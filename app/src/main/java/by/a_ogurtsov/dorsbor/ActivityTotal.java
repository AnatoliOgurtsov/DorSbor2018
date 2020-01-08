package by.a_ogurtsov.dorsbor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityTotal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    int setcolorToolbar(String value) {
        int color_toolbar;
        switch (value){
            case ("стандартная"):
                color_toolbar = R.color.primary_dark_base;
                break;
            case ("зеленая"):
                color_toolbar = R.color.primary_dark_green;
                break;
            case ("голубая"):
                color_toolbar = R.color.primary_dark_blue;
                break;
            case ("оранжевая"):
                color_toolbar = R.color.primary_dark_orange;
                break;
            case ("серая"):
                color_toolbar = R.color.primary_dark_grey;
                break;
            case ("бело-красная"):
                color_toolbar = R.color.primary_dark_wr;
                break;
            case ("красно-зеленая"):
                color_toolbar = R.color.primary_dark_rg;
                break;
            default: color_toolbar = R.color.primary_dark_base;
                break;
        }
        return color_toolbar;
    }

   void settheme(String value) {
        switch (value){
            case ("стандартная"):
                setTheme(R.style.DorSbor_theme_base);
                break;
            case ("зеленая"):
                setTheme(R.style.DorSbor_theme_green);
                break;
            case ("голубая"):
                setTheme(R.style.DorSbor_theme_blue);
                break;
            case ("оранжевая"):
                setTheme(R.style.DorSbor_theme_orange);
                break;
            case ("серая"):
                setTheme(R.style.DorSbor_theme_grey);
                break;
            case ("бело-красная"):
                setTheme(R.style.DorSbor_theme_wr);
                break;
            case ("красно-зеленая"):
                setTheme(R.style.DorSbor_theme_rg);
                break;
            default: setTheme(R.style.DorSbor_theme);
                break;
        }
    }
}
