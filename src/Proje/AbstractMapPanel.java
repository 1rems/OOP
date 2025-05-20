package Proje;
import javafx.scene.layout.Pane;
public abstract class AbstractMapPanel extends Pane{
	User user;
	public AbstractMapPanel(User user) {
        this.user=user;
    }

    public abstract void drawMap();


}
