package pro.viksit.com.viksit.assessment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class AssessmentFailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView header, info;
    private ProgressBar progress;
    private Button continu, viewReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_fail);

        header = (TextView) findViewById(R.id.tv_assess_fail_header);
        progress = (ProgressBar) findViewById(R.id.pb_assess_fail_progress);
        info = (TextView) findViewById(R.id.tv_assess_fail_info);
        viewReport = (Button) findViewById(R.id.btn_assess_fail_view_report);
        continu = (Button) findViewById(R.id.btn_assess_fail_continue);

        implementActions();
    }

    public void implementActions(){
        viewReport.setOnClickListener(this);
        continu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == viewReport.getId()){
            //do something
        } else  if(v.getId() == continu.getId()){
            //do something
        }
    }
}
