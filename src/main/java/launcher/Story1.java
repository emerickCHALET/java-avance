package launcher;

import org.tensorflow.Tensor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Story1 {
    private static int maxIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }

    public static Story1 storyOne() throws Exception {

        // Conversion d'une l'image vers le binaire
        TFUtils tf = new TFUtils();

        BufferedImage bi = ImageIO.read(new File("../java-avance/src/main/resources/inception5h/tensorPics/keyboard.jpg"));
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
        System.out.println(String.format("BEST MATCH: %s (%.2f%% likely)",
                labels.get(bestLabelIdx),
                resultProbs[bestLabelIdx] * 100f));
        String resultLabel= String.format("BEST MATCH: %s (%.2f%% likely)",
                labels.get(bestLabelIdx),
                resultProbs[bestLabelIdx] * 100f);
        return new Story1();
    }
}
