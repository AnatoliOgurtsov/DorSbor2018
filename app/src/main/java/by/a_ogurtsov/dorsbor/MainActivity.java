package by.a_ogurtsov.dorsbor;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerClickListener,
                                                               Fragment_info.Ok_clickListener{

    final String LOG_TAG = "myLogs";
    private String mTitle;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

        @SuppressWarnings("deprecation")
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



                if (savedInstanceState == null) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, Fragment_nalog_na_dorogi.newInstance(0, 0))
                            .commit();
                }

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle().toString();

        ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }



//==================================================================================================



          /*  mToolbar.setNavigationIcon(R.drawable.ic_drawer);*/

            mDrawerToggle = new ActionBarDrawerToggle(
                    this, //* host Activity *//*
                    mDrawerLayout, //* DrawerLayout object *//*
                    mToolbar, //* nav drawer icon to replace 'Up' caret *//*
                    R.string.drawer_open, //* "open drawer" description *//*
                    R.string.drawer_close //* "close drawer" description *//*
            ) {

                //** Called when a drawer has settled in a completely closed state. *//*
                public void onDrawerClosed(View view) {
                    Log.d(LOG_TAG, "открыто");
                    //getSupportActionBar().setTitle(mTitle);
                    supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                //** Called when a drawer has settled in a completely open state. *//*
                public void onDrawerOpened(View drawerView) {
                    Log.d(LOG_TAG, "закрыто");
                    //getSupportActionBar().setTitle(getResources().getString(R.string.app_name1));
                    supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };

// Set the drawer toggle as the DrawerListener
            mDrawerLayout.setDrawerListener(mDrawerToggle);
//==================================================================================================

        }

    @Override
    public void onNavigationDrawerItemSelected(String name) {   // вызывается из NavigationDrawerFragment
        // update the main content by replacing fragments
      onItemSelected(name);
    }

    public void onItemSelected(String name) {
        switch (name) {
            case "Dor_sbor":
                //mTitle = getString(R.string.title_section2);
                Log.d(LOG_TAG, getString(R.string.title_section2));
                Fragment_nalog_na_dorogi nnd;
                nnd = Fragment_nalog_na_dorogi.newInstance(0,0);
                getFragmentManager().beginTransaction().replace(R.id.container, nnd).commit();
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                break;
            case "Ocenit_prilozh":
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=by.a_ogurtsov.dorsbor"));
                startActivity(intent);
                break;
            case "About":
                Intent intent1 = new Intent(this, About.class);
                startActivity(intent1);
                break;
            case "info_OK":
                Log.d(LOG_TAG, "Привет из Info");
                Fragment_nalog_na_dorogi nnd1;
                nnd1 = Fragment_nalog_na_dorogi.newInstance(0,0);
                getFragmentManager().beginTransaction().replace(R.id.container, nnd1).commit();
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                break;
            case "Util_sbor":
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("market://details?id=by.a_ogurtsov.utilsbor"));
                startActivity(intent2);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
     //   boolean drawerOpen = mDrawerLayout.isDrawerOpen(R.id.navigation_drawer);
     //   menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Fragment_info fr_info;
            fr_info = Fragment_info.newInstance(mTitle, mTitle);
            getFragmentManager().beginTransaction().replace(R.id.container, fr_info).commit();
            mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
