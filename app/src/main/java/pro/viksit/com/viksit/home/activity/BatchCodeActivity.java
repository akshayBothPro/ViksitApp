package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class BatchCodeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = BatchCodeActivity.class.getSimpleName();

    private TextView title;
    private TextView info;
    private AppCompatEditText input1;
    private AppCompatEditText input2;
    private AppCompatEditText input3;
    private AppCompatEditText input4;

    private Button submitButton;
    private Button signin_different;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_code);

        title = (TextView) findViewById(R.id.tv_batchcode_title);
        info = (TextView) findViewById(R.id.tv_batchcode_info);
        input1 = (AppCompatEditText) findViewById(R.id.apet_bachcode_input1);
        input2 = (AppCompatEditText) findViewById(R.id.apet_bachcode_input2);
        input3 = (AppCompatEditText) findViewById(R.id.apet_bachcode_input3);
        input4 = (AppCompatEditText) findViewById(R.id.apet_bachcode_input4);
        /*input5 = (AppCompatEditText) findViewById(R.id.apet_bachcode_input5);
        input6 = (AppCompatEditText) findViewById(R.id.apet_bachcode_input6);*/
        submitButton = (Button) findViewById(R.id.btn_batchcode_submit);
        signin_different = (Button) findViewById(R.id.btn_batchcode_signin_different);


        implementListeners();
    }

    private void implementListeners(){
        submitButton.setOnClickListener(this);
        signin_different.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_batchcode_submit){
            //fetching batchcode input appending batchcode in a string
            String tmp = input1.getText().toString();
            StringBuffer batchCode = new StringBuffer();

            batchCode.append(tmp.trim());
            tmp  = input2.getText().toString();
            batchCode.append(tmp.trim());
            tmp  = input3.getText().toString();
            batchCode.append(tmp.trim());
            tmp  = input4.getText().toString();
            batchCode.append(tmp.trim());
                /*tmp  = input5.getText().toString();
                batchCode.append(tmp.trim());
                tmp  = input6.getText().toString();
                batchCode.append(tmp.trim());*/
            System.out.println("Batch Code: " + batchCode.toString());

        } else if (v.getId() == R.id.btn_batchcode_signin_different){
            startActivity(new Intent(BatchCodeActivity.this, HomeActivity.class));
        }
    }
}
