package pro.viksit.com.viksit.role.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class PurchaseSuccessActivity extends AppCompatActivity {

    private TextView title;
    private ImageView tick;
    private TextView info;
    private Button gotorole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_success);

        title = (TextView) findViewById(R.id.tv_purchase_success_title);
        tick = (ImageView) findViewById(R.id.iv_purchase_success_tick);
        info = (TextView) findViewById(R.id.tv_purchase_success_info);
        gotorole = (Button) findViewById(R.id.btn_purchase_success_gotorole);
    }
}
