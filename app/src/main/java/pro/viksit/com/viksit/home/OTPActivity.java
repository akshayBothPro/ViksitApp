package pro.viksit.com.viksit.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class OTPActivity extends AppCompatActivity {
    private static final String TAG = OTPActivity.class.getSimpleName();

    private TextView title;
    private TextView info;
    private AppCompatEditText otpInput;
    private Button enterOTP;
    private TextView resend;
    private TextView notNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        title = (TextView) findViewById(R.id.tv_otp_title);
        info = (TextView) findViewById(R.id.tv_otp_info);
        otpInput = (AppCompatEditText) findViewById(R.id.apet_otp_input);
        enterOTP = (Button) findViewById(R.id.btn_enter_otp);
        resend = (TextView) findViewById(R.id.tv_resend_otp);
        notNumber = (TextView) findViewById(R.id.tv_not_number);
    }

    private void implementListeners(){
        enterOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Enter OTP Button clicked");
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"resend OTP clicked");
            }
        });

        notNumber.setText(Html.fromHtml("<u>Not your number?</u>"));
        notNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"not your number clicked");
            }
        });
    }
}
