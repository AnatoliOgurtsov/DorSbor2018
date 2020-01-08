package by.a_ogurtsov.dorsbor;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

public class Fragment_preferences extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    SharedPreferences sharedPreferences;
    My_interface my_interface;
    final String STATE = "States";

    public interface My_interface {
        void onPreferenceChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(STATE, "OnCreatePreferenceFragment");
        addPreferencesFromResource(R.xml.preferences);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setSummary();
    }
    @Override
    public void onResume() {
        super.onResume();
        setSummary();
        getPreferenceScreen().getSharedPreferences()
          .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
           .unregisterOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference pref = findPreference(key);
        pref.setSummary("текущая: " + sharedPreferences.getString(key,""));
        my_interface.onPreferenceChanged();
    }


    private void setSummary(){
        Preference pref = findPreference("pref_theme");
        pref.setSummary("текущая: " + sharedPreferences.getString("pref_theme",""));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        my_interface = (My_interface)activity;

    }

}
