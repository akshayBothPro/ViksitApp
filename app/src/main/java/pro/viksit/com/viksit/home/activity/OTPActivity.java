package pro.viksit.com.viksit.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.telephony.SmsMessage;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.home.async.OTPAsync;

public class OTPActivity extends AppCompatActivity implements  View.OnClickListener{
    private static final String TAG = OTPActivity.class.getSimpleName();

    private TextView title, info, notNumber,tv_error_email,autoverify;
    private AppCompatEditText otpInput;
    private Button enterOTP, resend;
    private String otp,jsonresponse,phonenos;
    private ProgressBar progress;
    private LinearLayout main_layout;
    private BroadcastReceiver receiver;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if(getIntent()!= null){
            if(getIntent().getStringExtra("otp") != null){
                otp = getIntent().getStringExtra("otp");
            }
            if(getIntent().getStringExtra("phonenos") != null){
                phonenos = getIntent().getStringExtra("phonenos");
            }
            if(getIntent().getStringExtra("jsonresponse") != null){
                jsonresponse = getIntent().getStringExtra("jsonresponse");
            }

        }
        title = (TextView) findViewById(R.id.tv_otp_title);
        info = (TextView) findViewById(R.id.tv_otp_info);
        otpInput = (AppCompatEditText) findViewById(R.id.apet_otp_input);
        enterOTP = (Button) findViewById(R.id.btn_submit_otp);
        resend = (Button) findViewById(R.id.btn_resend_otp);
        notNumber = (TextView) findViewById(R.id.btn_not_number);
        tv_error_email = (TextView) findViewById(R.id.tv_error_email);
        progress = (ProgressBar) findViewById(R.id.progress);
        main_layout = (LinearLayout) findViewById(R.id.ll_otp_container);
        autoverify = (TextView) findViewById(R.id.autoverify);

        progress.setVisibility(View.VISIBLE);
        autoverify.setVisibility(View.VISIBLE);
        main_layout.setVisibility(View.GONE);
        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OTPActivity.this, "Auto Verification Failed! Try entering OTP manually",
                        Toast.LENGTH_LONG).show();
                autoverify.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                main_layout.setVisibility(View.VISIBLE);

            }};
        handler.postDelayed(runnable,60000);

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //do something based on the intent's action
                Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
                SmsMessage[] msgs = null;
                String msg_from;
                if (bundle != null){
                    //---retrieve the SMS message received---
                    progress.setVisibility(View.VISIBLE);
                    main_layout.setVisibility(View.GONE);
                    try{
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        msgs = new SmsMessage[pdus.length];
                        for(int i=0; i<msgs.length; i++){
                            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                            msg_from = msgs[i].getOriginatingAddress();



                            String msgBody = msgs[i].getMessageBody();
                            if(msg_from.contains("TLNTFY") ){
                                int cutindex = msgBody.indexOf("is");
                                int index = msgBody.length();
                                otpInput.setText(msgBody.substring(cutindex+2,index));
                                enterOTP.performClick();
                                autoverify.setVisibility(View.GONE);
                                handler.removeCallbacks(runnable);
                            }
                            System.out.println("msg is from me  "+msg_from+" and messgae is "+msgBody);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
//                            Log.d("Exception caught",e.getMessage());
                    }
                    progress.setVisibility(View.GONE);
                    main_layout.setVisibility(View.VISIBLE);
                }
            }
        };
        registerReceiver(receiver, filter);
        implementListeners();
    }

    private void implementListeners(){
        enterOTP.setOnClickListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resend.setOnClickListener(OTPActivity.this);
                resend.setBackground(getResources().getDrawable(R.drawable.signup_btn_shape));
            }},10000);

       /* notNumber.setText(Html.fromHtml("<u>Not your number?</u>"));*/
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

            validateOTP();

        } else if(v.getId() == R.id.btn_resend_otp){
            resend.setText("OTP has been re-sent.");
            resend.setBackgroundColor(getResources().getColor(R.color.white));
            resend.setTextColor(getResources().getColor(R.color.theme_color));
            new OTPAsync(this,jsonresponse,phonenos,progress,main_layout,tv_error_email).execute();


        } else if(v.getId() == R.id.btn_not_number){
            Log.i(TAG,"Submit OTP Button clicked");
        }
    }

    private void validateOTP() {

        if(otpInput != null && otpInput.getText() != null && !otpInput.getText().toString().equalsIgnoreCase("")){

            if(otpInput.getText().toString().trim().equalsIgnoreCase(otp)){
                Intent reset = new Intent(OTPActivity.this,ResetPasswordActivity.class);
                reset.putExtra("otp", otp);
                reset.putExtra("phonenos", phonenos);
                reset.putExtra("jsonresponse", jsonresponse);
                startActivity(reset);
            }else{
                tv_error_email.setText("OTP is invalid. Please enter a valid OTP.");
                tv_error_email.setVisibility(View.VISIBLE);
            }

        }else{
            tv_error_email.setText("OTP Field cannot be blank. Please enter OTP");
            tv_error_email.setVisibility(View.VISIBLE);
        }

    }


    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
