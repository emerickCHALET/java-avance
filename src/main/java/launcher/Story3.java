package launcher;

import org.tensorflow.Tensor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Story3 {

    public static List<String> storyThree(String description, String percent, File fileToSave) throws Exception{

        List<String> labelDescription = new ArrayList<String>(Arrays.asList(description.split(",")));
        // Conversion d'une l'image vers le binaire
        TFUtils tf = new TFUtils();

        File repertoire = new File(fileToSave.getPath());
        BufferedImage bi = ImageIO.read(new File(String.valueOf(repertoire)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        byte[] imgData = baos.toByteArray();
        System.out.println(imgData);

        // Conversion du binaire vers Tensor
        Tensor toTensor = tf.byteBufferToTensor(imgData);

        // Graph + label
        byte[] graphDef = Files.readAllBytes(Paths.get("../java-avance/src/main/resources/inception5h/tensorflow_inception_graph.pb"));
        List<String> labels = Files.readAllLines(Paths.get("../java-avance/src/main/resources/inception5h/labels.txt"));
        List<Integer> indexToTake = new ArrayList<Integer>();
        for (int i=0;i < labelDescription.size(); i++) {
            int test = labels.indexOf(labelDescription.get(i));
            indexToTake.add(test);
        }
        // Probs
        Tensor<Float> probs = tf.executeModelFromByteArray(graphDef, toTensor);

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
        List<String> result = new ArrayList<String>();

        for (int values:indexToTake) {
            int bestLabelIdx = values;
            float f=Float.parseFloat(percent);
            if ((resultProbs[bestLabelIdx] * 100f) >= f) {
                result.add(String.format("BEST MATCH: %s (%.2f%% likely)",
                        labels.get(bestLabelIdx),
                        resultProbs[bestLabelIdx] * 100f));
            }
            }
        return result;
    }

}
