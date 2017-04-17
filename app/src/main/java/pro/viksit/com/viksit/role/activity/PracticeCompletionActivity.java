package pro.viksit.com.viksit.role.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class PracticeCompletionActivity extends AppCompatActivity {

    private TextView title,subtitle;
    private ImageView tick;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_completion);

        title = (TextView) findViewById(R.id.tv_practice_title);
        subtitle = (TextView) findViewById(R.id.tv_practice_subtitle);
        tick = (ImageView) findViewById(R.id.iv_practice_tick);
        recycler = (RecyclerView) findViewById(R.id.rv_practice_completed);


    }
}
