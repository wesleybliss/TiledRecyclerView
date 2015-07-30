package tiledrecyclerview.gammagamma.com.tiledrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    protected RecyclerView listThings;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        String sampleImage1 = "http://assets3.thrillist.com/v1/image/1524495";
        String sampleImage2 = "http://assets3.thrillist.com/v1/image/1524494";
        String sampleImage3 = "http://assets3.thrillist.com/v1/image/1524492";

        List<CategoryTile> items = new ArrayList<>();
        items.add( new CategoryTile( CategoryTile.TYPE_TOP, sampleImage1, sampleImage2, sampleImage3 ) );
        items.add( new CategoryTile( CategoryTile.TYPE_LEFT, sampleImage1, sampleImage2, sampleImage3 ) );
        items.add( new CategoryTile( CategoryTile.TYPE_TOP, sampleImage1, sampleImage2, sampleImage3 ) );
        items.add( new CategoryTile( CategoryTile.TYPE_LEFT, sampleImage1, sampleImage2, sampleImage3 ) );

        TiledRecyclerViewAdapter tiledRecyclerViewAdapter = new TiledRecyclerViewAdapter( this, items );
        listThings = (RecyclerView) findViewById( R.id.listThings );
        setupRecyclerViewLayout( listThings );
        listThings.setAdapter( tiledRecyclerViewAdapter );

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    /**
     * A generic wrapper for setting a (required) {@link LinearLayoutManager} for a {@link RecyclerView}.
     *
     * @param recyclerView    Any {@link RecyclerView} instance
     */
    protected void setupRecyclerViewLayout( final RecyclerView recyclerView ) {
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setHasFixedSize( true );
    }

}