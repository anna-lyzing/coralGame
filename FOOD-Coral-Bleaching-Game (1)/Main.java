import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;


public class Main extends Application
{
    @Override
    public void start(Stage stage)
    {
        CoralModel model = new CoralModel();
        CoralView view = new CoralView();
        CoralController controller = new CoralController(model, view);

        // Starts the view (UI)
        view.start(stage);
    }

    public static void main(String args[])
    {
        launch(args);
    }
}