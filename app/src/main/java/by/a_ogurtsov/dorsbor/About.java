package by.a_ogurtsov.dorsbor;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Alexandr on 05.08.2015.
 */
public class About extends Activity {
    TextView textview1;
    String versionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        textview1 = (TextView)findViewById(R.id.Version);

        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        textview1.setText("Версия: " + versionName);


    }
}
