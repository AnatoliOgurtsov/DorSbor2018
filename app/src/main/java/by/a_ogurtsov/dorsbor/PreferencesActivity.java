package by.a_ogurtsov.dorsbor;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class PreferencesActivity extends ActivityTotal
                                 implements Fragment_preferences.My_interface{
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    final String LOG_TAG = "LogsP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("by.a_ogurtsov.dorsbor_preferences", MODE_PRIVATE);
        settheme(sharedPreferences.getString("pref_theme", ""));
        setContentView(R.layout.preferences_activity);

        inittoolbar();

        toolbar = (Toolbar) findViewById(R.id.toolbar_pref);
        toolbar.setBackgroundResource(setcolorToolbar(sharedPreferences.getString("pref_theme", "")));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_preferences()).commit();
    }


    private void inittoolbar(){

        toolbar = (Toolbar) findViewById(R.id.toolbar_pref);
        toolbar.setTitle(R.string.action_settings);
        //toolbar.setBackgroundResource(colorToolbar(sharedPreferences.getString("pref_theme", "")));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onPreferenceChanged() {
// перезапускаем PreferenceActivity для смены темы
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        Utils.onPreferenceChanged = true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(LOG_TAG, "BACKPRESSED!!!!!!!");
        if (Utils.onPreferenceChanged) {
            Utils.onPreferenceChangedToMain = true;
        }

    }
}
