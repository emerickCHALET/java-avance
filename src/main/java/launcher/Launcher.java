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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Launcher extends Application {

    //
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

        File repertoire = new File("../java-avance/src/main/resources/inception5h/tensorPics");
        File[] files = repertoire.listFiles();
        for (File f: files) {
            System.out.println("File" + f);

            BufferedImage bi = ImageIO.read(new File(String.valueOf(f)));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] imgData = baos.toByteArray();
            System.out.println(imgData);
            // Conversion du binaire vers Tensor
            Tensor toTensor = tf.byteBufferToTensor(imgData);
            System.out.println("To sensor :" + toTensor);


            // Graph + label
            byte[] graphDef = Files.readAllBytes(Paths.get("../java-avance/src/main/resources/inception5h/tensorflow_inception_graph.pb"));
            List<String> labels = Files.readAllLines(Paths.get("../java-avance/src/main/resources/inception5h/labels.txt"));


            // Probs
            Tensor<Float> probs = tf.executeModelFromByteArray(graphDef, toTensor);
            System.out.println("Probs: " + probs);
            // Result : [1, 1008]

            // Affichage prédiction
            final long[] rshape = probs.shape();
            if (probs.numDimensions() != 2 || rshape[0] != 1) {
                throw new RuntimeException(
                        String.format(
                                "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
                                Arrays.toString(rshape)));
            }
            int nlabels = (int) rshape[1];
            float[] resultProbs = probs.copyTo(new float[1][nlabels])[0];

            int bestLabelIdx = maxIndex(resultProbs);
            System.out.println( String.format("BEST MATCH: %s (%.2f%% likely)",
                    labels.get(bestLabelIdx),
                    resultProbs[bestLabelIdx] * 100f));
        }

    }
}
