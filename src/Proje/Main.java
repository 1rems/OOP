package Proje;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    private User currentUser;

    @Override
    public void start(Stage primaryStage) {
        currentUser = new User("İrem", 123);  // Sabit kullanıcı (giriş ekranı yoksa)

        ObservableList<Task> tasks = FXCollections.observableArrayList();

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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(taskTitleField, durationField, pointBox, datePicker, addButton, deleteButton, refreshButton, userInfo, taskList);

        Scene scene = new Scene(layout, 400, 500);
      //  scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        primaryStage.setTitle("Görev Takip Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}