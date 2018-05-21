package tiles;

import gfx.Assets;

public class WoodPlanksTile extends Tile {
    public WoodPlanksTile(int id) {
        super(Assets.woodPlanks, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
