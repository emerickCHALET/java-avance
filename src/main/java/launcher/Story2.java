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

public class Story2 {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Story");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((action) -> {
            System.out.println("Hello World!");
        });
    }
}



    /*int nlabels = (int) rshape[1];
    float[] resultProbs = probs.copyTo(new float[1][nlabels])[0];

    int bestLabelIdx = maxIndex(resultProbs);
            primaryStage.setTitle("Hello World!");
            btn.setText("description");
            btn.setOnAction((action)->{


    System.out.println( String.format("BEST MATCH: %s (%.2f%% likely)",
            labels.get(bestLabelIdx),
            resultProbs[bestLabelIdx] * 100f));

});
}*/