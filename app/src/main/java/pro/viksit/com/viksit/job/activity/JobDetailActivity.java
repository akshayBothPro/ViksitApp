package pro.viksit.com.viksit.job.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import pro.viksit.com.viksit.R;

public class JobDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        image = (ImageView) findViewById(R.id.iv_job_detail_image);


        setSupportActionBar(toolbar);
    }
}
