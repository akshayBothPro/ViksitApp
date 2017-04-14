package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by Feroz on 14-04-2017.
 */

public class Questiontimer extends CountDownTimer  {
    private Context context;
    private WaveLoadingView mWaveLoadingView;
    private long millisInFuture;
    public Questiontimer(Context context, WaveLoadingView mWaveLoadingView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mWaveLoadingView = mWaveLoadingView;
        this.context = context;
        this.millisInFuture = millisInFuture;

    }
        @Override
    public void onTick(long millisUntilFinished) {
            System.out.println("millisUntilFinished:::::::: "+millisUntilFinished);
            System.out.println("millisInFuture:::::::: "+millisInFuture);

            int progress =(int)(100 - (millisUntilFinished *100)/millisInFuture);
            System.out.println("progress  -> "+progress);
            mWaveLoadingView.setProgressValue(progress);

    }

    @Override
    public void onFinish() {

    }
}
