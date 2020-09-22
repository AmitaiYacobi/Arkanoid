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
 * The Wide easy level.
 */
public class WideEasy implements LevelInformation {
    private int ballsNum;
    private int blocksNum;
    private int ballSpeed;

    public WideEasy() {
        this.ballsNum = 10;
        this.ballSpeed = 10;
        this.blocksNum = 15;

    }
    @Override
    public int numberOfBalls() {
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        int x = 300;
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity newVel = Velocity.fromAngleAndSpeed(x, this.ballSpeed);
            velocityList.add(newVel);
            x += 12;
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 350;
    }

    @Override
    public String levelName() {
        return "Level Name: Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite rec = new Background(this);
        return rec;
    }

    @Override
    public List<Block> blocks() {
        double blockWidth = 52;
        int blockHeight = 20;
        int boardWidth = 800;
        int narrowRect = 10;
        int startHeight = 250;


        double x = boardWidth - narrowRect - blockWidth;
        double y = narrowRect + startHeight;
        double rowsNum = 1;
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.green, Color.green, Color.green, Color.BLUE, Color.BLUE, Color.pink, Color.pink,
                Color.CYAN, Color.CYAN};
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < rowsNum; i++) {
            for (int j = i; j < this.numberOfBlocksToRemove(); j++) {
                Block block = new Block(new Point(x, y), (int) blockWidth, blockHeight, colors[j]);
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
        return this.blocksNum;
    }

    @Override
    public void setDraw(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(10, 10, 790, 590);
        d.setColor(Color.orange);
        for (int i = 680; i > 50; i -= 13) {
            d.drawLine(150, 150, i, 275);
        }
        d.setColor(Color.PINK);
        d.fillCircle(150, 150, 60);
        d.setColor(Color.orange);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.yellow);
        d.fillCircle(150, 150, 40);
    }
}
