package tiles;

import gfx.Assets;

public class DarkGrassTile extends Tile {
    public DarkGrassTile(int id) {
        super(Assets.darkGrass, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
