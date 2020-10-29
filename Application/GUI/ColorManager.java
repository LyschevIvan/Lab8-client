package com.company.Application.GUI;

import javafx.scene.paint.Color;

import java.util.Random;

public class ColorManager {
    private static final Random randomColor = new Random(123);
    public static Color nextColor(){
        return Color.color(randomColor.nextFloat(),randomColor.nextFloat(),randomColor.nextFloat(),1);

    }
    public static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
