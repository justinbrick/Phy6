package core;

import java.awt.*;

public class SandPixel extends Entity
{

    public SandPixel(Vector2 pos)
    {
        super(Color.YELLOW, pos);
    }

    public void tick()
    {
        if (isSpaceBelow())
            moveDown();
        else if (isSpaceLeft())
            moveLeft();
        else if (isSpaceRight())
            moveRight();
        hasTicked = true;
    }
}
