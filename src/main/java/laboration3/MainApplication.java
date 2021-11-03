package laboration3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class  MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lab3-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
        scene.getStylesheets().add(getClass().getResource("css/stylesheet.css").toExternalForm());
        stage.setTitle("~~ LABORATION 3 ~~");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}