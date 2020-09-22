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
 * The Direct hit level.
 */

public class DirectHit implements LevelInformation {
    private int ballsNum;
    private int blocksNum;
    private int angle;
    private int ballSpeed;

    public DirectHit() {
        this.ballsNum = 1;
        this.blocksNum = 1;
        this.angle = 0;
        this.ballSpeed = 10;
    }


    @Override
    public int numberOfBalls() {
        return ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity newVel = Velocity.fromAngleAndSpeed(this.angle, this.ballSpeed);
        velocityList.add(newVel);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        int speed = 6;
        return speed;
    }

    @Override
    public int paddleWidth() {
        int width = 90;
        return width;
    }

    @Override
    public String levelName() {
        return "Level Name: Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite rec = new Background(this);
        return rec;
    }

    @Override
    public List<Block> blocks() {
        int width = 20;
        int height = 20;
        double x = 390;
        double y = 100;
        Block[] blocks = new Block[this.numberOfBlocksToRemove()];
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            blocks[i] = new Block(new Point(x, y), width, height, Color.RED);
            blockList.add(blocks[i]);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksNum;
    }

    @Override
    public void setDraw(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(10, 10, 790, 590);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 110, 60);
        d.drawCircle(400, 110, 80);
        d.drawCircle(400, 110, 100);
        d.drawLine(400, 95, 400, 0);
        d.drawLine(400, 125, 400, 220);
        d.drawLine(385, 110, 285, 110);
        d.drawLine(415, 110, 515, 110);
    }

}
