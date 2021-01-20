package launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        TextField txt = new TextField();
        //Image img = new Image();
        Button btns = new Button();
        Story1 story1 = new Story1();
        btns.setText("Story1");
        //txt.setText(Story1.storyOne());
        btns.setOnAction((action) -> {
            try {
                System.out.println(Story1.storyOne());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(btns);
        root.getChildren().add(txt);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();


    }
}
