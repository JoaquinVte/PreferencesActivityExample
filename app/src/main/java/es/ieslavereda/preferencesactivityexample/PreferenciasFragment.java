package es.ieslavereda.preferencesactivityexample;


import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Arrays;
import java.util.List;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preferencias, rootKey);

        // Modificacion de la vista de preferencias por codigo
        // ListPreference
        final ListPreference unidades = findPreference("unidades");
        final List<String> unidades_entries = Arrays.asList(getResources().getStringArray(R.array.unidades_entries));
        final List<String> unidades_values = Arrays.asList(getResources().getStringArray(R.array.unidades_values));

        int pos  = unidades_values.indexOf(GestionPreferencias.getInstance().getUnidades(getContext()));

        unidades.setSummary("Unidades en " + unidades_entries.get(pos));
        unidades.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                int pos  = unidades_values.indexOf(newValue);
                unidades.setSummary("Unidades en " + unidades_entries.get(pos));

                return true;
            }
        });

        // EditTextPreference
        final EditTextPreference editTextPreference = findPreference("editTextPreferenceKey");
        editTextPreference.setSummary("Actualmente: " + GestionPreferencias.getInstance().getEditTextPreference(getContext()));
        editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                editTextPreference.setSummary("Actualmente: " + newValue);
                return true;
            }
        });

        // Theme preferences with ListPreference
        ListPreference themePreference = getPreferenceManager().findPreference(getString(R.string.settings_theme_key));
        if (themePreference.getValue() == null) {
            themePreference.setValue(ThemeSetup.Mode.DEFAULT.name());
        }
        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeSetup.applyTheme(ThemeSetup.Mode.valueOf((String) newValue));
            return true;
        });
    }
}
