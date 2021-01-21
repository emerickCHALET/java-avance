package launcher;

import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Filters {

    public Effect filtersList(String listFilter) {
        Lighting lighting = new Lighting();
        switch (listFilter) {
            case "Red": //Action for this item
                lighting.setDiffuseConstant(1.0);
                lighting.setSpecularConstant(0.0);
                lighting.setSpecularExponent(0.0);
                lighting.setSurfaceScale(0.0);
                lighting.setLight(new Light.Distant(45, 45, Color.RED));
                break;
            case "Green": //Action for this item
                lighting.setDiffuseConstant(1.0);
                lighting.setSpecularConstant(0.0);
                lighting.setSpecularExponent(0.0);
                lighting.setSurfaceScale(0.0);
                lighting.setLight(new Light.Distant(45, 45, Color.GREEN));
                break;
            case "Blue": //Action for this item
                lighting.setDiffuseConstant(1.0);
                lighting.setSpecularConstant(0.0);
                lighting.setSpecularExponent(0.0);
                lighting.setSurfaceScale(0.0);
                lighting.setLight(new Light.Distant(45, 45, Color.BLUE));
                break;
            default: //Default action
                break;
        }
        return lighting;
    }
}
