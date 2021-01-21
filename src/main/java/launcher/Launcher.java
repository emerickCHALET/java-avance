package launcher;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    File getFile = new File("");
    File fileToSave = new File("");
    Java2DFrameConverter converter = new Java2DFrameConverter();
    
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
        /*Story3 story3 = new Story3();

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
        primaryStage.show();*/

         primaryStage.setTitle("Story 4");
        Label label = new Label();
        label.setTranslateY(80);

        ImageView cam = new ImageView();
        grabber.setImageWidth(300);
        grabber.setImageHeight(300);

        Button imgButton = new Button("Select Image");
        imgButton.setTranslateY(50);

        Button cameraButton = new Button("Start Camera");

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
                    view.setFitHeight(70);
                    view.setFitWidth(70);
                    label.setGraphic(view);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            else{
                label.setText("save cancel");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(imgButton);
        root.getChildren().add(label);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();


        //Story 5
        /*Story5 story5 = new Story5();
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
        primaryStage.show();*/

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
