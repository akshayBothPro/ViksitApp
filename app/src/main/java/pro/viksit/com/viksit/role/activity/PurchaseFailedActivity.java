package pro.viksit.com.viksit.role.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class PurchaseFailedActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView image;
    private TextView title, desc;
    private Button tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_failed);

        image = (ImageView) findViewById(R.id.iv_purchase_failed_image);
        title = (TextView) findViewById(R.id.tv_purchase_failed_title);
        desc = (TextView) findViewById(R.id.tv_purchase_failed_info);
        tryAgain = (Button) findViewById(R.id.btn_purchase_failed_tryagain);

        implementActions();

    }

    public void implementActions(){
        tryAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == tryAgain.getId()){
            //do something
        }
    }
}
