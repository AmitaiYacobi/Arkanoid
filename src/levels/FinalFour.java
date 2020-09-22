//316418300
package levels;

import biuoop.DrawSurface;
import general.Velocity;
import geometry.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Background;
import sprites.collidables.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Final four level.
 */
public class FinalFour implements LevelInformation {
    private int ballsNum;
    private int blocksNum;
    private int ballSpeed;

    public FinalFour() {
        this.ballsNum = 3;
        this.ballSpeed = 10;
        this.blocksNum = 105;

    }

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(300, ballSpeed);
        Velocity v2 = Velocity.fromAngleAndSpeed(60, ballSpeed);
        Velocity v3 = Velocity.fromAngleAndSpeed(0, ballSpeed);
        velocityList.add(v1);
        velocityList.add(v3);
        velocityList.add(v2);
        return velocityList;
    }



    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level Name: Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite rec = new Background(this);
        return rec;
    }

    @Override
    public List<Block> blocks() {
        int blockWidth = 52;
        int blockHeight = 20;
        int boardWidth = 800;
        int narrowRect = 10;
        int startHeight = 100;


        double x = boardWidth - narrowRect - blockWidth;
        double y = narrowRect + startHeight;
        double rowsNum = 7;
        Color[] colors = {Color.darkGray, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.cyan};
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Point(x, y), (int) blockWidth, blockHeight, colors[i]);
                blockList.add(block);
                x -= blockWidth;
            }
            x = boardWidth - narrowRect - blockWidth;
            y += blockHeight;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocksNum;
    }

    @Override
    public void setDraw(DrawSurface d) {
        d.setColor(new Color(40, 120, 180));
        d.fillRectangle(10, 10, 790, 590);
        d.setColor(Color.white);
        for (int i = 150; i > 80; i -= 8) {
            d.drawLine(i, 390, i - 15, 600);
        }
        d.setColor(Color.GRAY);
        d.fillCircle(90, 420, 20);
        d.setColor(Color.GRAY);
        d.fillCircle(120, 420, 20);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(110, 390, 25);
        d.setColor(Color.GRAY);
        d.fillCircle(140, 400, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(80, 400, 20);
        for (int i = 650; i > 580; i -= 8) {
            d.drawLine(i, 500, i - 15, 600);
        }
        d.setColor(Color.GRAY);
        d.fillCircle(590, 530, 20);
        d.setColor(Color.GRAY);
        d.fillCircle(620, 530, 20);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(610, 500, 25);
        d.setColor(Color.GRAY);
        d.fillCircle(640, 510, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(580, 510, 20);

    }
}
