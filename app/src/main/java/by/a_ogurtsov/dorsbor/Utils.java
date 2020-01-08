package by.a_ogurtsov.dorsbor;


import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public final class Utils {

    static final Double bazovaya_vel = 27.0;
    static boolean onPreferenceChanged;
    static boolean onPreferenceChangedToMain;

    @ColorInt
    public static int getThemeColor(@NonNull Context context, @AttrRes final int attributeColor){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, typedValue, true);
     return typedValue.data;
    }

}
