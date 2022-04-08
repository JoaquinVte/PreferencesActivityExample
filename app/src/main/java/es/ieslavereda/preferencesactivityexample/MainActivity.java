package es.ieslavereda.preferencesactivityexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThemeSetup.applyPreferenceTheme(getApplicationContext());

        TextView textView = findViewById(R.id.mis_preferencias);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Preferencias \n"+
                        "EditTextPreferences: " + GestionPreferencias.getInstance().getEditTextPreference(getApplicationContext()) + "\n" +
                        "CheckBoxPreferences: " + GestionPreferencias.getInstance().getCheckBoxPreference(getApplicationContext()) + "\n" +
                        "ListPreferences: " + GestionPreferencias.getInstance().getUnidades(getApplicationContext()) +"\n" +
                        "Theme: " + GestionPreferencias.getInstance().getTheme(getApplicationContext()));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.configuracion):
                Intent intentPreferenciasActivity = new Intent(this, PreferenciasActivity.class);
                startActivity(intentPreferenciasActivity);
                return true;
            case (R.id.exit):
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}