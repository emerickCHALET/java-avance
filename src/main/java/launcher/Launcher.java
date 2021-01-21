package launcher;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    File fileToSave = new File("");
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
        /*Story2 story2 = new Story2();

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
        primaryStage.show();*/

        //Story 3
//        Story3 story3 = new Story3();
//
//        primaryStage.setTitle("Story 3");
//
//        Label label = new Label();
//        label.setTranslateY(55);
//
//        Label label1 = new Label();
//        label1.setTranslateY(70);
//
//        Label label2 = new Label();
//        label2.setTranslateY(90);
//
//        TextField textField = new TextField("Enter Description");
//        textField.setTranslateY(-30);
//        textField.setDisable(true);
//
//        TextField textField1 = new TextField("Indiquez le pourcentage minimum");
//        textField1.setTranslateY(-60);
//        textField1.setDisable(true);
//
//        Button button1 = new Button("Save File");
//        button1.setTranslateY(30);
//        button1.setDisable(true);
//        button1.setOnAction(e -> {
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            directoryChooser.setTitle("Save file");
//            File selectedDirectory = directoryChooser.showDialog(new Stage());
//            if (selectedDirectory != null) {
//                try {
//                    BufferedImage bufferedImage = ImageIO.read(fileToSave);
//                    ImageIO.write(bufferedImage, "jpg", new File(selectedDirectory.getPath() +"/"+ fileToSave.getName() + ".jpg"));
//                    label1.setText("save realised");
//                } catch (IOException f) {
//                    label1.setText("File couldn't be saved.");
//                }
//            }else{
//                label1.setText("save cancel");
//            }
//        });
//
//        Button button = new Button("Validate");
//        button.setDisable(true);
//        button.setOnAction(e -> {
//            String description = textField.getText();
//            String percent = textField1.getText();
//            try {
//                List<String> result = Story3.storyThree(description,percent,fileToSave);
//                button1.setDisable(true);
//                label2.setText("");
//                for (String values:result) {
//                    if(!values.equals("")){
//                        label2.setText(values);
//                        button1.setDisable(false);
//                    }
//                }
//
//
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
//
//        Button button2 = new Button("Select Image");
//        button2.setTranslateY(-90);
//        button2.setOnAction(e -> {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Save file");
//            fileChooser.setInitialDirectory(new File("../java-avance/src/main/resources/inception5h/tensorPics/"));
//            fileChooser.getExtensionFilters().addAll(
//                    new FileChooser.ExtensionFilter("JPG", "*.jpg"));
//            File selectedfile = fileChooser.showOpenDialog(new Stage());
//            if (selectedfile != null) {
//                fileToSave = selectedfile;
//                textField.setDisable(false);
//                textField1.setDisable(false);
//                button.setDisable(false);
//                label.setText("File selected : " + selectedfile.getName());
//            }
//            else{
//                label.setText("save cancel");
//            }
//        });
//
//
//        StackPane root = new StackPane();
//        root.getChildren().add(button);
//        root.getChildren().add(button1);
//        root.getChildren().add(button2);
//        root.getChildren().add(label);
//        root.getChildren().add(label1);
//        root.getChildren().add(label2);
//        root.getChildren().add(textField);
//        root.getChildren().add(textField1);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();

        //Story 6
        /*Button button = new Button("Add filter to image");
        button.setTranslateY(80);
        FileInputStream is = new FileInputStream("../java-avance/src/main/resources/inception5h/tensorPics/mouse.jpg");
        Image image = new Image(is);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        ComboBox filterList = new ComboBox();
        Filters filter = new Filters();
        filterList.setTranslateY(-70);
        filterList.getItems().addAll("Red", "Green", "Blue");

        button.setOnAction(e -> {
            imageView.setEffect(filter.filtersList(filterList.getValue().toString())); // Apply filter to the image
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        root.getChildren().add(imageView);
        root.getChildren().add(filterList);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();*/
    }
}
