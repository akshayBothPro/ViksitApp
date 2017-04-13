package pro.viksit.com.viksit.challenge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardProfile;

public class LeaderBoardActivity extends AppCompatActivity {

    private AppCompatSpinner dropdown;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> spinnerAdapter;
    private Toolbar toolbar;
    private ImageButton goBack;
    private TextView barTitle;
    private ArrayList<LeaderBoardProfile> profileList;
    private ImageView first, second,third;
    private Button firstRank, secondRank, thirdRank;
    private TextView firstName, secondName, thirdName;
    private TextView firstXP, secondXP, thirdXP;

    private int screenWidth,screenHeight;
    private double diagonalInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        goBack = (ImageButton) findViewById(R.id.btn_back);
        barTitle = (TextView) findViewById(R.id.tv_notification_toolbar_title);
        dropdown = (AppCompatSpinner) findViewById(R.id.spin_dropdown);


        setSupportActionBar(toolbar);
    }

    private void implementActions(){
        spinnerList = new ArrayList<String>();
        spinnerList.add("Automobile");
        spinnerList.add("Business Services");
        spinnerList.add("Computers");
        spinnerList.add("Education");
        spinnerList.add("Personal");
        spinnerList.add("Travel");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(spinnerAdapter);
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

    public ArrayList<LeaderBoardProfile> setDummyData(ArrayList<LeaderBoardProfile> list){
        LeaderBoardProfile profile;
        list = new ArrayList<>();

        for(int i = 0 ; i < 4; i++) {
            profile = new LeaderBoardProfile(R.drawable.ic_3, "Urvashi", 5, 2596);
            list.add(profile);

            profile = new LeaderBoardProfile(R.drawable.ic_5, "Riya", 5, 4796);
            list.add(profile);

            profile = new LeaderBoardProfile(R.drawable.ic_7, "Deepika", 5, 4896);
            list.add(profile);

            profile = new LeaderBoardProfile(R.drawable.ic_9, "Disha", 5, 4996);
            list.add(profile);
        }

        return list;
    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        float yInches= displaymetrics.heightPixels/displaymetrics.ydpi;
        float xInches= displaymetrics.widthPixels/displaymetrics.xdpi;
        diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
    }
}
