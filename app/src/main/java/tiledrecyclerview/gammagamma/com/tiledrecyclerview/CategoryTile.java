package tiledrecyclerview.gammagamma.com.tiledrecyclerview;

import android.support.annotation.IntDef;


public class CategoryTile {

    public static final String TAG = CategoryTile.class.getSimpleName();

    public static final int TYPE_TOP = 0;
    public static final int TYPE_LEFT = 1;

    @IntDef({ TYPE_TOP, TYPE_LEFT })
    public @interface TileType {}

    public @CategoryTile.TileType int type;
    public String imageUrl1;
    public String imageUrl2;
    public String imageUrl3;

    public CategoryTile( final @CategoryTile.TileType int type, final String imageUrl1, final String imageUrl2, final String imageUrl3 ) {
        this.type = type;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

}