package by.a_ogurtsov.dorsbor;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActivityTotal implements NavigationDrawerFragment.NavigationDrawerClickListener,
                                                               Fragment_info.Ok_clickListener{

    final String LOG_TAG = "myLogs";
    final String STATE = "States";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;


        @Override
    protected void onCreate(Bundle savedInstanceState) {

            Log.d(STATE, "OnCreateMainActivity");

            PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
            sharedPreferences = getSharedPreferences("by.a_ogurtsov.dorsbor_preferences", MODE_PRIVATE);

            settheme(sharedPreferences.getString("pref_theme", ""));
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            inittoolbar();

            if (savedInstanceState == null) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment_nalog_na_dorogi.newInstance(0, 0))
                        .commit();
            }


            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayoutActivate(mDrawerLayout);

        }
//==================================================================================================



    public void onItemSelected(String name) {
        FragmentManager fragmentManager = getFragmentManager();
        int i = 0;  // вложенность бэкстека
        switch (name) {
            case "DorSbor":
            case "info_OK":  // нажатие кнопки ОК во франменте Инфо
                i = fragmentManager.getBackStackEntryCount();
                if ( i!= 0) {
                    fragmentManager.popBackStack();
                }
                getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                break;
            case "Info":
                i = fragmentManager.getBackStackEntryCount();
                Log.d(LOG_TAG, "Привет из Info");
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                getSupportActionBar().setTitle(getResources().getString(R.string.info_nav));
                Fragment_info fragment_info;
                fragment_info = Fragment_info.newInstance("0","0");
                if (i == 0) {
                    getFragmentManager().beginTransaction().add(R.id.container, fragment_info, "Info").addToBackStack(null).commit();
                }
                break;
            case "Ocenit_prilozh":
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=by.a_ogurtsov.dorsbor"));
                startActivity(intent);
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                break;
            case "About":
                Intent intent1 = new Intent(this, About.class);
                startActivity(intent1);
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                break;
            case "Util_sbor":
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("market://details?id=by.a_ogurtsov.utilsbor"));
                startActivity(intent2);
                mDrawerLayout.closeDrawer(findViewById(R.id.navigation_drawer));
                break;

        }
    }

   private void inittoolbar(){
        Typeface font = Typeface.createFromAsset(getAssets(), "font/fontawesome.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(setcolorToolbar(sharedPreferences.getString("pref_theme", "")));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onNavigationDrawerItemSelected(String name) {   // вызывается из NavigationDrawerFragment
        // update the main content by replacing fragments
        onItemSelected(name);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        Log.d(STATE, "onPostCreate");
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        Log.d(STATE, "onConfigurationChanged");
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_pref:
                Utils.onPreferenceChanged = false;
                Utils.onPreferenceChangedToMain = false;
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;

                case android.R.id.home:
                if (getFragmentManager().findFragmentByTag("Info") != null) {
                getFragmentManager().popBackStack();
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                }

            break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(STATE, "OnResumeMainActivity");
        if (Utils.onPreferenceChangedToMain){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(STATE, "OnPauseMainActivity");
        Utils.onPreferenceChangedToMain = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(STATE, "OnStopMainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(STATE, "OnDestroyMainActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(STATE, "OnStartMainActivity");
    }
    private void drawerLayoutActivate(DrawerLayout drawerLayout){
        mDrawerToggle = new ActionBarDrawerToggle(
                this, //* host Activity *//*
                mDrawerLayout, //* DrawerLayout object *//*
                toolbar, //* nav drawer icon to replace 'Up' caret *//*
                R.string.drawer_open, //* "open drawer" description *//*
                R.string.drawer_close //* "close drawer" description *//*
        ) {

            //** Called when a drawer has settled in a completely closed state. *//*
            public void onDrawerClosed(View view) {

                Log.d(LOG_TAG, "открыто");
                Log.d(STATE, "onDrawerClosed");
                //getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerOpened(View drawerView) {

                Log.d(LOG_TAG, "закрыто");
                Log.d(STATE, "onDrawerOpened");
                //getSupportActionBar().setTitle(getResources().getString(R.string.app_name1));
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
      //  drawerLayout.removeDrawerListener(mDrawerToggle);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("Info");
        if (fragment != null) {

            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
        super.onBackPressed();
    }
}


