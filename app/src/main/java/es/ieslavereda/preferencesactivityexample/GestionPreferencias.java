package es.ieslavereda.preferencesactivityexample;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class GestionPreferencias {

    private static SharedPreferences pref;

    private static void inicializa(Context context) {
        if (pref == null)
            pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getUnidades(Context context){
        inicializa(context);
        return pref.getString("unidades","standard");
    }


}