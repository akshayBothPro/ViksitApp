package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Date;

import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;

/**
 * Created by Feroz on 14-04-2017.
 */

public class AssessmentTotalTimer extends CountDownTimer {
    private Context context;
    private TextView timer;
    private long millisInFuture;
    public AssessmentTotalTimer(Context context,TextView timer,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.timer = timer;
        this.context = context;
        this.millisInFuture = millisInFuture;
    }


    @Override
    public void onTick(long millisUntilFinished) {
        ((AssessmentActivity)context).remaining_time = millisUntilFinished;
        int seconds = (int) ((millisUntilFinished) / 1000) % 60 ;
        int minutes = (int) ((( millisUntilFinished) / (1000*60)) % 60);
        int hours   = (int) (((millisUntilFinished) / (1000*60*60)) % 24);
        String display_hours =hours+"";
        String displayminutes = minutes+"";
        String displayseconds = seconds+"";
        if(hours <10){
            display_hours ="0"+display_hours;
        }
        if(minutes<10){
            displayminutes = "0"+displayminutes;
        }
        if(seconds<10){
            displayseconds = "0"+displayseconds;
        }
        timer.setText(display_hours+":"+displayminutes+":"+displayseconds);


    }

    @Override
    public void onFinish() {
        timer.setText("times up");
        ((AssessmentActivity)context).submitAssessment();
    }
}
