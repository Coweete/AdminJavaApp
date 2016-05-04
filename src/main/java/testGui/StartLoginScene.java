package testGui;

import com.essence.Controller.AppController;
import com.essence.Gui.LoginScene;
import com.essence.Gui.SetUpScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-26.
 */
public class StartLoginScene extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("hello");

        //LoginScene scene = new LoginScene();
        SetUpScene scene = new SetUpScene();
        AppController cont = new AppController();
        Button btn = new Button("click me");
        btn.setOnAction(event -> {
            //scene.display();
            scene.display(cont);
            primaryStage.close();
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(btn);
        primaryStage.setScene(new Scene(layout,300,300));
        primaryStage.show();
    }
}
