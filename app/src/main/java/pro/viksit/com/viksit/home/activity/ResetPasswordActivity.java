package pro.viksit.com.viksit.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.home.async.ResetAsync;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title,tv_error_email;
    private AppCompatEditText newPassword;
    private AppCompatEditText confirmPassword;
    private Button submit;
    private Button signindifferent;
    private ProgressBar progress;
    private LinearLayout main_layout;
    private String otp,jsonresponse,phonenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
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
        title = (TextView) findViewById(R.id.tc_reset_title);
        newPassword = (AppCompatEditText) findViewById(R.id.apet_new_password);
        confirmPassword = (AppCompatEditText) findViewById(R.id.apet_confirm_password);
        submit = (Button) findViewById(R.id.btn_reset_submit);
        signindifferent = (Button) findViewById(R.id.btn_sign_in_different);
        tv_error_email = (TextView) findViewById(R.id.tv_error_email);
        progress = (ProgressBar) findViewById(R.id.progress);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        submit.setOnClickListener(this);



        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_error_email.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_error_email.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_reset_submit:
                if(newPassword.getText() != null && !newPassword.getText().toString().equalsIgnoreCase("")
                        && confirmPassword.getText() != null && !confirmPassword.getText().toString().equalsIgnoreCase("") && newPassword.getText().toString().trim().equalsIgnoreCase(confirmPassword.getText().toString().trim())){

                new ResetAsync(this,jsonresponse,otp,phonenos,progress,main_layout,tv_error_email,newPassword.getText().toString().trim()).execute();
                }else{
                    String errormsg = "";
                    if(newPassword.getText() == null || newPassword.getText().toString().equalsIgnoreCase("")){
                        errormsg = "Password cannot be empty";
                        tv_error_email.setText(errormsg);
                        tv_error_email.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(confirmPassword.getText() == null || confirmPassword.getText().toString().equalsIgnoreCase("")) {
                        errormsg = "Confirm Password cannot be empty";
                        tv_error_email.setText(errormsg);
                        tv_error_email.setVisibility(View.VISIBLE);
                        break;
                    }
                    if(!newPassword.getText().toString().equalsIgnoreCase(confirmPassword.toString())){
                            errormsg ="Password and Confirm password are not matching";
                        tv_error_email.setText(errormsg);
                        tv_error_email.setVisibility(View.VISIBLE);
                    }
                    }
                break;

        }
    }
}
