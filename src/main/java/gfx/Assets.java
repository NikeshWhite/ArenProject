package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage dirt, grass, stone, darkGrass, stoneBrick, gravel, logicBlockBlueActive, logicBlockBlueNotActive;
    public static BufferedImage player, player1, player2, black, home, gate;

    public static Image opening, lab, logic, afterLogic;


    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));

        opening = ImageLoader.loadImage("/textures/opening.png");
        lab = ImageLoader.loadImage("/textures/lab.png");
        logic = ImageLoader.loadImage("/textures/logic.png");
        afterLogic = ImageLoader.loadImage("/textures/afterLogic.png");

        dirt = sheet.crop(0, 0, width, height);
        grass = sheet.crop(width, 0, width, height);
        stone = sheet.crop(width * 2, 0, width, height);
        darkGrass = sheet.crop(width * 3, 0, width, height);
        stoneBrick = sheet.crop(0, height, width, height);
        gravel = sheet.crop(width * 2, height, width, height);

        logicBlockBlueActive = sheet.crop(0, height * 2, width, height);
        logicBlockBlueNotActive = sheet.crop(width, height * 2, width, height);

        player1 = sheet.crop(width * 5, height * 4, width, height);
        player = sheet.crop(width * 4, height * 4, width, height);
        black = sheet.crop(0, height * 3, width, height);

        home = sheet.crop(width * 4, 0, width * 2, height * 2);
        gate = sheet.crop(width * 4, height * 2, width * 2, height);
    }
}
