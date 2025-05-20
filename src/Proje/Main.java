package Proje;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main extends Application {

    private User currentUser;
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<Task> missedTasks = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        currentUser = new User("İrem", 123);  // Sabit kullanıcı (giriş ekranı yoksa)
        tasks.setAll(Task.loadFromDatabase(currentUser.getUserID()));



        // Görev alanı
        TextField taskTitleField = new TextField();
        taskTitleField.setPromptText("Görev başlığı");

        TextField durationField = new TextField();
        durationField.setPromptText("Süre (dk)");

        ComboBox<Integer> pointBox = new ComboBox<>();
        pointBox.getItems().addAll(10, 30, 50);
        pointBox.setPromptText("Puan");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Tarih seç");

        ListView<Task> taskList = new ListView<>(tasks);

        // Görev Ekle
        Button addButton = new Button("Görev Ekle");
        addButton.setOnAction(e -> {
            try {
                String title = taskTitleField.getText().trim();
                int duration = Integer.parseInt(durationField.getText().trim());
                int point = pointBox.getValue();
                LocalDate date = datePicker.getValue();

                if (title.isEmpty() || date == null) throw new Exception();

                Task task = new Task(title, duration, date, point);
                task.saveToDatabase(currentUser.getUserID());
                tasks.add(task);


                taskTitleField.clear();
                durationField.clear();
                pointBox.setValue(null);
                datePicker.setValue(null);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Lütfen tüm alanları doğru şekilde doldurun.");
                alert.showAndWait();
            }
        });

        // Görev tamamlama (çift tıklama)
        taskList.setCellFactory(lv -> {
            ListCell<Task> cell = new ListCell<>() {
                @Override
                protected void updateItem(Task task, boolean empty) {
                    super.updateItem(task, empty);
                    setText((empty || task == null) ? null : task.toString());
                }
            };

            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty() && e.getClickCount() == 2) {
                    Task selectedTask = cell.getItem();
                    if (!selectedTask.getIsCompleted()) {
                        currentUser.completeTask(selectedTask);
                        selectedTask.setIsCompleted(true);
                        taskList.refresh();
                    }
                }
            });

            return cell;
        });

        // Silme butonu
        Button deleteButton = new Button("Görevi Sil");
        deleteButton.setOnAction(e -> {
            Task selected = taskList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                currentUser.deleteTask(selected);
                tasks.remove(selected);
            }
        });

        // Kullanıcı bilgisi etiketi
        Label userInfo = new Label(currentUser.toString());

        // Puan/Seviye güncelleyici
        Button refreshButton = new Button("Kullanıcı Bilgilerini Güncelle");
        refreshButton.setOnAction(e -> userInfo.setText(currentUser.toString()));
        
        // Seviye İlerlemesi butonu
        Button showLevelButton = new Button("Seviye");
        showLevelButton.setOnAction(e -> {
            Stage levelStage = new Stage();
            GamePanel gamePanel = new GamePanel(currentUser);
            Scene levelScene = new Scene(gamePanel, 700, 200);
            levelStage.setTitle("Seviye İlerlemesi");
            levelStage.setScene(levelScene);
            levelStage.show();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(taskTitleField, durationField, pointBox, datePicker, addButton, deleteButton, refreshButton,showLevelButton, userInfo, taskList);

        Scene scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        primaryStage.setTitle("Görev Takip Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        startMissedTaskChecker();
        
    }
    private void startMissedTaskChecker() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            for (Task task : new ArrayList<>(tasks)) {
                if (!task.getIsCompleted()) {
                    LocalDateTime deadline = task.getCreatedTime().plusMinutes(task.getdurationMinutes());
                    if (now.isAfter(deadline)) {
                        Platform.runLater(() -> {
                            tasks.remove(task);
                            missedTasks.add(task);
                            System.out.println("Kaçırılan görev: " + task.getTitle());
                        });
                    }
                }
            }
        }, 0, 1, TimeUnit.MINUTES); // Her dakika kontrol et
    }
    public static void main(String[] args) {
        launch(args);
    }

	public ObservableList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ObservableList<Task> tasks) {
		this.tasks = tasks;
	}

	public ObservableList<Task> getMissedTasks() {
		return missedTasks;
	}

	public void setMissedTasks(ObservableList<Task> missedTasks) {
		this.missedTasks = missedTasks;
	}
}