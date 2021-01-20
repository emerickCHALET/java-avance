package launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.nio.file.Paths;
import java.util.List;

public class Launcher extends Application {

    private static int maxIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }


    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((action)->{
            System.out.println("Hello World!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        // Conversion d'une l'image vers le binaire
        TFUtils tf = new TFUtils();
        BufferedImage bi = ImageIO.read(new File("/Users/laurentsem/Documents/Projects/java-avance/src/main/resources/inception5h/tensorPics/jack.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        byte[] imgData = baos.toByteArray();
        System.out.println(imgData);
        // Conversion du binaire vers Tensor
        Tensor toTensor = tf.byteBufferToTensor(imgData);
        System.out.println("To sensor :" + toTensor);

        // Conversion de toutes les images
        //byte[] allImg = Files.readAllBytes(Paths.get("/Users/laurentsem/Documents/Projects/java-avance/src/main/resources/inception5h/tensorPics"));
        byte[] graphDef = Files.readAllBytes(Paths.get("/Users/laurentsem/Documents/Projects/java-avance/src/main/resources/inception5h/tensorflow_inception_graph.pb"));
        List<String> labels = Files.readAllLines(Paths.get("/Users/laurentsem/Documents/Projects/java-avance/src/main/resources/inception5h/labels.txt"));


        Tensor probs = tf.executeModelFromByteArray(graphDef, toTensor);
        System.out.println("Probs: " + probs);


        // Pour exécuter le modèle afin de sortir les probs


    }
}
