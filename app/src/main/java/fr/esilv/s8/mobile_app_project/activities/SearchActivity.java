package fr.esilv.s8.mobile_app_project.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import fr.esilv.s8.mobile_app_project.R;

public class SearchActivity extends AppCompatActivity {
    private AutoCompleteTextView textView;
    private Button search_button;
    public static final String PREFS_NAME = "PingBusPrefs";
    public static final String PREFS_SEARCH_HISTORY = "SearchHistory";
    private SharedPreferences settings;
    private Set<String> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Autocomplete textView & sharedPreferences
        settings = getSharedPreferences(PREFS_NAME, 0);
        history = settings.getStringSet(PREFS_SEARCH_HISTORY, new HashSet<String>());
        setAutoCompleteSource();
        textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_search);

        search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addSearchInput(textView.getText().toString());
                search();
            }
        });

        textView.setOnKeyListener(new View.OnKeyListener()
        {
            //Add the keyword search entered to the sharedPreferences
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER)) {
                    addSearchInput(textView.getText().toString());
                    search();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        savePrefs();
    }

    private void search(){
        VideosActivity.start(this, textView.getText().toString());
    }

    private void setAutoCompleteSource() {
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_search);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, history.toArray(new String[history.size()]));
        textView.setAdapter(adapter);
    }

    private void addSearchInput(String input) {
        if (!history.contains(input))
        {
            history.add(input);
            setAutoCompleteSource();
        }
    }

    private void savePrefs() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(PREFS_SEARCH_HISTORY, history);

        editor.commit();
    }

}
