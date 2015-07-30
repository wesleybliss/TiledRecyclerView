package tiledrecyclerview.gammagamma.com.tiledrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TiledRecyclerViewAdapter extends RecyclerView.Adapter<TiledRecyclerViewAdapter.ViewHolder> {

    public static final String TAG = TiledRecyclerViewAdapter.class.getSimpleName();

    protected List<CategoryTile> items;

    protected View selectedView;     // Current selected view
    protected int selectedPosition;  // Current selected position
    protected TiledRecyclerViewCallbacks tiledRecyclerViewCallbacks;

    protected WeakReference<Picasso> picassoRef;

    // Map of possible tile types and their corresponding layouts
    protected static HashMap<Integer, Integer> tilesViewMap;
    static {
        tilesViewMap = new HashMap<>();
        tilesViewMap.put( CategoryTile.TYPE_TOP, R.layout.row_tile_top );
        tilesViewMap.put( CategoryTile.TYPE_LEFT, R.layout.row_tile_left );
    }

    /**
     * An adapter to handle grouped items in a {@link RecyclerView}.
     *
     * @param picasso         A Picasso instance for image loading (becomes a {@link WeakReference<Picasso>})
     * @param items           A list of {@link CategoryTile}s
     */
    public TiledRecyclerViewAdapter( final Picasso picasso, final List<CategoryTile> items ) {
        this.items = new ArrayList<>( items );
        this.picassoRef = new WeakReference<>( picasso );
    }

    /**
     * Gets any {@link TiledRecyclerViewCallbacks} set for this adapter instance.
     */
    public TiledRecyclerViewCallbacks getTiledRecyclerViewCallbacks() {
        return tiledRecyclerViewCallbacks;
    }

    /**
     * Sets {@link TiledRecyclerViewCallbacks} for this adapter instance.
     *
     * @param TiledRecyclerViewCallbacks    Callbacks to get notified of item events
     */
    public void setTiledRecyclerViewCallbacks( TiledRecyclerViewCallbacks TiledRecyclerViewCallbacks ) {
        this.tiledRecyclerViewCallbacks = TiledRecyclerViewCallbacks;
    }

    public List<CategoryTile> getitems() { return items; }

    /**
     * Since items of multiple type are not supported, this is used to get the current list index.
     *
     * @param position    Selected list index
     * @return            Selected list index
     */
    @Override
    public int getItemViewType( int position ) {
        return position;
    }

    /**
     * Initial creation of a list item view.
     * This typically only happens once, while {@link #onBindViewHolder(ViewHolder, int)} gets called multiple times.
     *
     * @param viewGroup    Parent view group of row
     * @param position     Index of the item being inflated
     */
    @Override
    public TiledRecyclerViewAdapter.ViewHolder onCreateViewHolder( final ViewGroup viewGroup, final int position ) {

        final CategoryTile item = items.get( position );

        final View view = LayoutInflater.from( viewGroup.getContext() )
            .inflate( tilesViewMap.get( item.type ), viewGroup, false );

        final ViewHolder viewHolder = new ViewHolder( view );

        viewHolder.itemView.setClickable( false );

        return viewHolder;

    }

    /**
     * Handles setting data on view elements.
     * This gets called many times as the list items come into/out of view.
     *
     * @param viewHolder    A {@link TiledRecyclerViewAdapter.ViewHolder} instance
     * @param position      Index of list item being rendered
     */
    @Override
    public void onBindViewHolder( final TiledRecyclerViewAdapter.ViewHolder viewHolder, final int position ) {

        final CategoryTile item = items.get( position );

        if ( picassoRef.get() != null ) {
            picassoRef.get().load( item.imageUrl1 ).noPlaceholder().noFade().fit().into( viewHolder.image1 );
            picassoRef.get().load( item.imageUrl2 ).noPlaceholder().noFade().fit().into( viewHolder.image2 );
            picassoRef.get().load( item.imageUrl3 ).noPlaceholder().noFade().fit().into( viewHolder.image3 );
        }

    }

    /**
     * Gets the total number of items.
     */
    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image1;
        public ImageView image2;
        public ImageView image3;

        public ViewHolder( View itemView ) {
            super( itemView );
            image1 = (ImageView) itemView.findViewById( R.id.image1 );
            image2 = (ImageView) itemView.findViewById( R.id.image2 );
            image3 = (ImageView) itemView.findViewById( R.id.image3 );

            image1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    // @todo trigger callbacks
                    Log.d( TAG, "image1 was clicked" );
                }
            });
        }

    }

}