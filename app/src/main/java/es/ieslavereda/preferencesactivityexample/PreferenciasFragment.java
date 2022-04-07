package es.ieslavereda.preferencesactivityexample;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


import java.util.ArrayList;
import java.util.Arrays;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preferencias, rootKey);

        final ListPreference unidades = (ListPreference) findPreference("unidades");
        final String[] unidadesEntries = getResources().getStringArray(R.array.unidades_entries);
        final String[] unidadesValues = getResources().getStringArray(R.array.unidades_values);
        int pos  = (new ArrayList<String>(Arrays.asList(unidadesValues))).indexOf(String.valueOf(GestionPreferencias.getUnidades(getContext())));

        unidades.setSummary(unidades.getSummary() + "\n" + getResources().getString(R.string.currently) + " " + unidadesEntries[pos]);
        unidades.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                int pos  = (new ArrayList<String>(Arrays.asList(unidadesValues))).indexOf(newValue);
                unidades.setSummary("Unidades en " + "\n" + getResources().getString(R.string.currently) + " " + unidadesEntries[pos]);

                return true;
            }
        });

    }


}
