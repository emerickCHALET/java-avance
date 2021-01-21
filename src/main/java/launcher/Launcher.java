package launcher;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    File getFile = new File("");
    File fileToSave = new File("");
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    Java2DFrameConverter converter = new Java2DFrameConverter();
    String url = "";
    @Override
    public void start(Stage primaryStage) throws Exception {

        //startStoryOne();
        //startStoryTwo(primaryStage);
        //startStoryThree(primaryStage);
        //startStoryFour(primaryStage);
        //startStoryFive(primaryStage);
        //startStorySix(primaryStage);
        startStorySeven(primaryStage);

    }
    public void startStoryOne() throws Exception {
        //Story 1
        Story1 story1 = new Story1();
        Story1.storyOne();
        List<String> list = new ArrayList<String>(Story1.storyOne());
        for (String value: list
             ) {
            System.out.println(value);
        }
    }

    public void startStoryTwo(Stage primaryStage) throws Exception {
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

    public void startStoryThree(Stage primaryStage){
        //Story 3
        Story3 story3 = new Story3();

        primaryStage.setTitle("Story 3");

        Label label = new Label();
        label.setTranslateY(55);

        Label label1 = new Label();
        label1.setTranslateY(70);

        Label label2 = new Label();
        label2.setTranslateY(90);

        TextField textField = new TextField("Enter Description");
        textField.setTranslateY(-30);
        textField.setDisable(true);

        TextField textField1 = new TextField("Indiquez le pourcentage minimum");
        textField1.setTranslateY(-60);
        textField1.setDisable(true);

        Button button1 = new Button("Save File");
        button1.setTranslateY(30);
        button1.setDisable(true);
        button1.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Save file");
            File selectedDirectory = directoryChooser.showDialog(new Stage());
            if (selectedDirectory != null) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(fileToSave);
                    ImageIO.write(bufferedImage, "jpg", new File(selectedDirectory.getPath() +"/"+ fileToSave.getName() + ".jpg"));
                    label1.setText("save realised");
                } catch (IOException f) {
                    label1.setText("File couldn't be saved.");
                }
            }else{
                label1.setText("save cancel");
            }
        });

        Button button = new Button("Validate");
        button.setDisable(true);
        button.setOnAction(e -> {
            String description = textField.getText();
            String percent = textField1.getText();
            try {
                List<String> result = Story3.storyThree(description,percent,fileToSave);
                button1.setDisable(true);
                label2.setText("");
                for (String values:result) {
                    if(!values.equals("")){
                        label2.setText(values);
                        button1.setDisable(false);
                    }
                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button button2 = new Button("Select Image");
        button2.setTranslateY(-90);
        button2.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            fileChooser.setInitialDirectory(new File("../java-avance/src/main/resources/inception5h/tensorPics/"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"));
            File selectedfile = fileChooser.showOpenDialog(new Stage());
            if (selectedfile != null) {
                fileToSave = selectedfile;
                textField.setDisable(false);
                textField1.setDisable(false);
                button.setDisable(false);
                label.setText("File selected : " + selectedfile.getName());
            }
            else{
                label.setText("save cancel");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        root.getChildren().add(label);
        root.getChildren().add(label1);
        root.getChildren().add(label2);
        root.getChildren().add(textField);
        root.getChildren().add(textField1);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public void startStoryFour(Stage primaryStage) throws FileNotFoundException {
        //Story 4
        primaryStage.setTitle("Story 4");
        Label label = new Label();
        label.setTranslateY(80);

        Label label1 = new Label();
        label1.setTranslateY(300);

        ImageView cam = new ImageView();
        grabber.setImageWidth(300);
        grabber.setImageHeight(300);

        Button start_cameraButton = new Button("Start Camera");
        start_cameraButton.setTranslateY(270);
        start_cameraButton.setTranslateX(-200);
        start_cameraButton.setOnAction(e ->{
            try {
                cam.setVisible(true);
                startCam(cam);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button stop_cameraButton = new Button("Stop Camera");
        stop_cameraButton.setTranslateY(270);
        stop_cameraButton.setTranslateX(-100);
        stop_cameraButton.setOnAction(e ->{
            try{
                stopCam();
                cam.setVisible(false);
            }catch (Exception exception){}
        });

        Button takePhotoButton = new Button("take photo");
        takePhotoButton.setTranslateY(270);
        takePhotoButton.setOnAction(e -> {
            try {
                Frame frame = grabber.grab();
                BufferedImage bufferedImage = converter.getBufferedImage(frame);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(300);
                imageView.setFitWidth(400);
                try {
                    ImageIO.write(bufferedImage,"jpg", new File("../java-avance/src/main/resources/inception5h/tensorPics/savephoto.jpg"));
                }catch (Exception exception){}
                url = "../java-avance/src/main/resources/inception5h/tensorPics/savephoto.jpg";
                label.setGraphic(imageView);
                stopCam();
                cam.setVisible(false);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        });

        Button imgButton = new Button("Select Image");
        imgButton.setTranslateY(270);
        imgButton.setTranslateX(100);
        imgButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("../java-avance/src/main/resources/inception5h/tensorPics/"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"));
            File selectedfile = fileChooser.showOpenDialog(new Stage());
            if (selectedfile != null) {
                getFile = selectedfile;
                try {
                    InputStream is = new FileInputStream(getFile);
                    Image image = new Image(is);
                    ImageView view = new ImageView(image);
                    view.setFitHeight(200);
                    view.setFitWidth(200);
                    url = selectedfile.getPath();
                    label.setGraphic(view);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            else{
                label.setText("save cancel");
            }
        });

        Button launchCalculButton = new Button("Launch Calcul");
        launchCalculButton.setTranslateY(270);
        launchCalculButton.setTranslateX(200);
        launchCalculButton.setOnAction(e -> {
            try {
                label1.setText(Story4.storyFour(url));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(imgButton);
        root.getChildren().add(start_cameraButton);
        root.getChildren().add(stop_cameraButton);
        root.getChildren().add(takePhotoButton);
        root.getChildren().add(launchCalculButton);
        root.getChildren().add(label);
        root.getChildren().add(label1);
        root.getChildren().add(cam);
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }

    public void startStoryFive(Stage primaryStage) throws Exception {

        //Story 5
        primaryStage.setTitle("Story 5");

        ImageView cam = new ImageView();
        grabber.setImageWidth(300);
        grabber.setImageHeight(300);

        Button button = new Button("Launch webcam");
        button.setOnAction(e -> {
            try {
                cam.setVisible(true);
                startCam(cam);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button button2 = new Button("Stop camera");
        button2.setTranslateY(265);
        button2.setOnAction(e->{
            try{
                stopCam();
                cam.setVisible(false);
            }catch (Exception exception){}
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        root.getChildren().add(button2);
        root.getChildren().add(cam);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void startStorySix(Stage primaryStage) throws Exception {

        //Story 6

        primaryStage.setTitle("Story 6");
        Button button = new Button("Add filter to image");
        button.setTranslateY(80);
        FileInputStream is = new FileInputStream("../java-avance/src/main/resources/inception5h/tensorPics/mouse.jpg");
        Image image = new Image(is);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        ComboBox filterList = new ComboBox();
        Filters filter = new Filters();
        filterList.setTranslateY(-70);
        filterList.getItems().addAll("Red", "Green", "Blue","");

        button.setOnAction(e -> {
            imageView.setEffect(filter.filtersList(filterList.getValue().toString())); // Apply filter to the image
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        root.getChildren().add(imageView);
        root.getChildren().add(filterList);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public void startStorySeven(Stage primaryStage) throws Exception {
        //Story7

        primaryStage.setTitle("Story 7");
        Button button = new Button("Add filter to image");
        button.setTranslateY(100);

        FileInputStream is = new FileInputStream("../java-avance/src/main/resources/inception5h/tensorPics/mouse.jpg");
        Image image = new Image(is);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label label = new Label();

        ComboBox filterList = new ComboBox();
        Filters filter = new Filters();
        filterList.setTranslateY(-90);
        filterList.getItems().addAll("Red", "Green", "Blue", "");

        ComboBox cadreList = new ComboBox();
        cadreList.setTranslateY(-130);
        cadreList.getItems().addAll("cadre1","cadre2","");

        button.setOnAction(e -> {
            if (filterList.getValue() != null){
                imageView.setEffect(filter.filtersList(filterList.getValue().toString())); // Apply filter to the image
            }else if (filterList.getSelectionModel().getSelectedItem() == ""){
                imageView.setEffect(null);
            }
            if (cadreList.getSelectionModel().getSelectedItem() != ""){
                String name = cadreList.getValue().toString();
                try {
                    InputStream us = new FileInputStream("../java-avance/src/main/resources/inception5h/cadre/"+name+".png");
                    Image images = new Image(us);
                    ImageView view = new ImageView(images);
                    view.setFitHeight(145);
                    view.setFitWidth(145);
                    label.setGraphic(view);
                    label.setVisible(true);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }else if (cadreList.getSelectionModel().getSelectedItem() == ""){
                label.setVisible(false);
            }
        });

        Button resetButton = new Button("reset");
        resetButton.setTranslateY(130);
        resetButton.setOnAction(e ->{
            label.setVisible(false);
            imageView.setEffect(null);
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);
        root.getChildren().add(resetButton);
        root.getChildren().add(imageView);
        root.getChildren().add(filterList);
        root.getChildren().add(cadreList);
        root.getChildren().add(label);
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    private void stopCam() {
        try {
            grabber.stop();
        }catch (Exception e){}
    }

    private void startCam(ImageView cam) {
        try{
            grabber.start();
        }catch (Exception e){}

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getFrame(cam);
            }
        };

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable,0,40, TimeUnit.MILLISECONDS);
    }

    private void getFrame(ImageView cam) {

        try {
            org.bytedeco.javacv.Frame frame = grabber.grab();
            if(frame != null){
                WritableImage image = frameToImage(frame);
                cam.setImage(image);
            }
        } catch (Exception e){}
    }

    private WritableImage frameToImage(Frame frame) {
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
