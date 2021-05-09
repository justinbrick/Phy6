package core;

import java.awt.*;

public class WoodPixel extends Entity
{

    private static final Color BROWN = new Color(54, 43, 14);

    public WoodPixel(Vector2 pos)
    {
        super(PixelType.WOOD, BROWN, pos);
    }

    public void tick()
    {
        // TODO: Add fire implementation.
    }
}
