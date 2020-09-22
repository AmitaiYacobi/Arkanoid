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
 * The Green 3 level.
 */
public class Green3 implements LevelInformation {
    private int ballsNum;
    private int blocksNum;
    private int ballSpeed;

    public Green3 () {
        this.ballsNum = 2;
        this.ballSpeed = 10;
        this.blocksNum = 40;
    }

    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(300, 10);
        Velocity v2 = Velocity.fromAngleAndSpeed(60, 10);
        velocityList.add(v1);
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
        return "Level Name: Green 3";
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
        int startHeight = 150;

        double x = boardWidth - narrowRect - blockWidth;
        double y = narrowRect + startHeight;
        double rowsNum = 5;
        Color[] colors = {Color.darkGray, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < rowsNum; i++) {
            for (int j = i; j < 10; j++) {
                Block block = new Block(new Point(x, y), blockWidth, blockHeight, colors[i]);
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
        d.setColor(new Color(52, 235, 50));
        d.fillRectangle(10, 10, 790, 590);
        d.setColor(Color.BLACK);
        d.fillRectangle(75, 520, 150, 40);
        d.fillRectangle(94, 490, 112, 30);
        d.setColor(Color.darkGray);
        for (int i = 90; i < 220; i += 10) {
            d.fillCircle(i, 570, 10);
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(206, 500, 100, 10);
        for (int i = 316; i < 800; i += 35) {
            d.setColor(Color.pink);
            d.fillCircle(i, 505, 10);
            d.setColor(Color.orange);
            d.fillCircle(i, 505, 8);
            d.setColor(Color.pink);
            d.fillCircle(i, 505, 5);
        }
        d.setColor(Color.WHITE);
        d.fillRectangle(100, 500, 20, 20);
    }
}
