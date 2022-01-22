package vn.icar.taxicontacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefHelper {
    private static vn.icar.taxicontacts.PrefHelper instance = null;
    private Context context;

    public static vn.icar.taxicontacts.PrefHelper getInstance(Context context) {
        if (instance == null) {
            return new vn.icar.taxicontacts.PrefHelper(context);
        } else {
            return instance;
        }
    }

    public PrefHelper(Context context) {
        this.context = context;
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Integer getUserName() {
        return getSharedPreferences().getInt("Username",0);
    }

    public void setUsername(Integer status) {

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt("Username", status);
        editor.apply();
    }

    public String getPassword() {
        return getSharedPreferences().getString("Password","");
    }

    public void setLicensePlate(String status) {

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("Password", status);
        editor.apply();
    }
}
