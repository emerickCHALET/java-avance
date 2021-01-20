package launcher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;


public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Story 1
        /*Story1 story1 = new Story1();
        Story1.storyOne();
        List<String> list = new ArrayList<String>(Story1.storyOne());
        for (String value: list
             ) {
            System.out.println(value);
        }*/

        //Story2
        Story2 story2 = new Story2();

        primaryStage.setTitle("Story 2");
        InputStream is = new FileInputStream("../java-avance/src/main/resources/inception5h/tensorPics/mouse.jpg");
        Image image = new Image(is);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label label = new Label(Story2.storyTwo());
        label.setMinWidth(100);
        label.setMinHeight(50);
        label.setPadding(new Insets(120,0,0,30));
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        root.getChildren().add(label);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();


    }
}
