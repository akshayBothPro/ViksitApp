package pro.viksit.com.viksit.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class OTPActivity extends AppCompatActivity implements  View.OnClickListener{
    private static final String TAG = OTPActivity.class.getSimpleName();

    private TextView title;
    private TextView info;
    private AppCompatEditText otpInput;
    private Button enterOTP;
    private Button resend;
    private TextView notNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        title = (TextView) findViewById(R.id.tv_otp_title);
        info = (TextView) findViewById(R.id.tv_otp_info);
        otpInput = (AppCompatEditText) findViewById(R.id.apet_otp_input);
        enterOTP = (Button) findViewById(R.id.btn_submit_otp);
        resend = (Button) findViewById(R.id.btn_resend_otp);
        notNumber = (TextView) findViewById(R.id.btn_not_number);
    }

    private void implementListeners(){
        enterOTP.setOnClickListener(this);

        resend.setOnClickListener(this);

        notNumber.setText(Html.fromHtml("<u>Not your number?</u>"));
        notNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"not your number clicked");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_submit_otp){
            Log.i(TAG,"Submit OTP Button clicked");
        } else if(v.getId() == R.id.btn_resend_otp){
            resend.setText("OTP has been re-sent.");
            resend.setTextColor(getResources().getColor(R.color.theme_color));
        } else if(v.getId() == R.id.btn_not_number){
            Log.i(TAG,"Submit OTP Button clicked");
        }
    }
}
