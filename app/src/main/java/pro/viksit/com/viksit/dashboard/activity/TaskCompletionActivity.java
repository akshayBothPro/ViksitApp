package pro.viksit.com.viksit.dashboard.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.util.BottomBarUtil;
import pro.viksit.com.viksit.job.activity.JobActivity;

public class TaskCompletionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private TextView title;
    private ImageView tick;
    private TextView info;
    private Button getMoreTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_completion);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        title = (TextView) findViewById(R.id.tv_task_completion_title);
        tick = (ImageView) findViewById(R.id.iv_task_completion_tick);
        info = (TextView) findViewById(R.id.tv_task_completion_info);
        getMoreTasks = (Button) findViewById(R.id.btn_task_completion_getmore);

        setSupportActionBar(toolbar);
        new BottomBarUtil().setupBottomBar(bottomNavigationView,TaskCompletionActivity.this,R.id.task);//setting bottom navigation bar

    }
}
