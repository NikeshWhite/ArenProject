package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage dirt, grass, stone, darkGrass, stoneBrick, gravel, logicBlockBlueActive, logicBlockBlueNotActive, black, woodPlanks, blueKey;
    public static BufferedImage player, player1, player2, home, gate, woodenGate, woodenGateOpen;

    public static BufferedImage playerUp, playerDown, playerLeft, playerRight,
            playerAttackUp, playerAttackRight, playerAttackDown, playerAttackLeft;

    public static BufferedImage arenaBossUp, arenaBossDown, arenaBossLeft, arenaBossRight,
            arenaBossAttackUp, arenaBossAttackRight, arenaBossAttackDown, arenaBossAttackLeft;

    public static BufferedImage arenaEnemySt1Up, arenaEnemySt1Down, arenaEnemySt1Left, arenaEnemySt1Right,
            arenaEnemySt1AttackUp, arenaEnemySt1AttackRight, arenaEnemySt1AttackDown, arenaEnemySt1AttackLeft;

    public static BufferedImage arenaEnemySt2Up, arenaEnemySt2Down, arenaEnemySt2Left, arenaEnemySt2Right,
            arenaEnemySt2AttackUp, arenaEnemySt2AttackRight, arenaEnemySt2AttackDown, arenaEnemySt2AttackLeft;

    public static BufferedImage healthBar;

    public static Image opening, lab, logic, startSt2, end;


    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));
        SpriteSheet players = new SpriteSheet(ImageLoader.loadImage("/textures/motion.png"));
        SpriteSheet boss = new SpriteSheet(ImageLoader.loadImage("/textures/somebody.png"));
        SpriteSheet arenaEnemySt1 = new SpriteSheet(ImageLoader.loadImage("/textures/arenaenemyst1.png"));
        SpriteSheet arenaEnemySt2 = new SpriteSheet(ImageLoader.loadImage("/textures/arenaEnemySt2.png"));

        opening = ImageLoader.loadImage("/textures/open.png");
        lab = ImageLoader.loadImage("/textures/labyrinth.png");
        logic = ImageLoader.loadImage("/textures/logic.png");
        startSt2 = ImageLoader.loadImage("/textures/afterLogic.png");
        end = ImageLoader.loadImage("/textures/end.png");

        healthBar = ImageLoader.loadImage("/textures/healthbar.png");

        dirt = sheet.crop(0, 0, width, height);
        grass = sheet.crop(width, 0, width, height);
        stone = sheet.crop(width * 2, 0, width, height);
        darkGrass = sheet.crop(width * 3, 0, width, height);
        stoneBrick = sheet.crop(0, height, width, height);
        gravel = sheet.crop(width * 2, height, width, height);
        black = sheet.crop(width * 2, height * 3, width, height);
        woodPlanks = sheet.crop(width * 3, height, width, height);

        logicBlockBlueActive = sheet.crop(0, height * 2, width, height);
        logicBlockBlueNotActive = sheet.crop(width, height * 2, width, height);
        blueKey = sheet.crop(width * 4, height * 3, width, height);

        player1 = sheet.crop(width * 5, height * 4, width, height);
        player = sheet.crop(width * 4, height * 4, width, height);

        playerUp = players.crop(width, 0, width, height);
        playerDown = players.crop(0, 0, width, height);
        playerLeft = players.crop(width, height, width, height);
        playerRight = players.crop(0, height, width, height);

        playerAttackUp = players.crop(width, height * 3, width, height);
        playerAttackRight = players.crop(0, height * 2, width, height);
        playerAttackDown = players.crop(0, height * 3, width, height);
        playerAttackLeft = players.crop(width, height * 2, width, height);

        arenaBossUp = boss.crop(width, 0, width, height);
        arenaBossDown = boss.crop(0, 0, width, height);
        arenaBossLeft = boss.crop(width, height, width, height);
        arenaBossRight = boss.crop(0, height, width, height);

        arenaBossAttackUp = boss.crop(width, height * 2, width, height);
        arenaBossAttackDown = boss.crop(0, height * 2, width, height);
        arenaBossAttackRight = boss.crop(0, height * 3, width, height);
        arenaBossAttackLeft = boss.crop(width, height * 3, width, height);

        arenaEnemySt1Up = arenaEnemySt1.crop(width, 0, width, height);
        arenaEnemySt1Down = arenaEnemySt1.crop(0, 0, width, height);
        arenaEnemySt1Left = arenaEnemySt1.crop(width, height, width, height);
        arenaEnemySt1Right = arenaEnemySt1.crop(0, height, width, height);

        arenaEnemySt1AttackUp = arenaEnemySt1.crop(width, height * 3, width, height);
        arenaEnemySt1AttackDown = arenaEnemySt1.crop(0, height * 3, width, height);
        arenaEnemySt1AttackRight = arenaEnemySt1.crop(0, height * 2, width, height);
        arenaEnemySt1AttackLeft = arenaEnemySt1.crop(width, height * 2, width, height);

        arenaEnemySt2Up = arenaEnemySt2.crop(width, 0, width, height);
        arenaEnemySt2Down = arenaEnemySt2.crop(0, 0, width, height);
        arenaEnemySt2Left = arenaEnemySt2.crop(width, height, width, height);
        arenaEnemySt2Right = arenaEnemySt2.crop(0, height, width, height);

        arenaEnemySt2AttackUp = arenaEnemySt2.crop(width, height * 3, width, height);
        arenaEnemySt2AttackDown = arenaEnemySt2.crop(0, height * 3, width, height);
        arenaEnemySt2AttackRight = arenaEnemySt2.crop(0, height * 2, width, height);
        arenaEnemySt2AttackLeft = arenaEnemySt2.crop(width, height * 2, width, height);

        home = sheet.crop(width * 4, 0, width * 2, height * 2);
        gate = sheet.crop(width * 4, height * 2, width * 2, height);
        woodenGate = sheet.crop(width * 6, height * 2, width * 2, height);
        woodenGateOpen = sheet.crop(width * 6, height * 3, width * 2, height);
    }
}
