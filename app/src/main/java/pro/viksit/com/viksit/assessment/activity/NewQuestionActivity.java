package pro.viksit.com.viksit.assessment.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;

public class NewQuestionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button goBack;
    private Button send;
    private Spinner dropdown;
    private List<String> modules;
    private EditText question;
    private EditText description;
    private ArrayAdapter<String> dataAdapter;
    private ImageButton bold;
    private ImageButton italic;
    private ImageButton underline;
    private ImageButton quotes;
    private ImageButton check;
    private ImageButton attach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        goBack = (Button) findViewById(R.id.btn_back);
        send = (Button) findViewById(R.id.btn_send);
        dropdown = (Spinner) findViewById(R.id.spn_dropdown);
        question = (EditText) findViewById(R.id.et_ask_question);
        description = (EditText) findViewById(R.id.et_ques_description);
        bold = (ImageButton) findViewById(R.id.ibtn_bold);
        italic = (ImageButton) findViewById(R.id.ibtn_italic);
        underline = (ImageButton) findViewById(R.id.ibtn_underline);
        quotes = (ImageButton) findViewById(R.id.ibtn_quotes);
        check = (ImageButton) findViewById(R.id.ibtn_check);
        attach = (ImageButton) findViewById(R.id.ibtn_attach);

        setSupportActionBar(toolbar);
        implementActions();
    }

    private void implementActions(){
        modules = new ArrayList<String>();
        modules.add("Automobile");
        modules.add("Business Services");
        modules.add("Computers");
        modules.add("Education");
        modules.add("Personal");
        modules.add("Travel");

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modules);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
