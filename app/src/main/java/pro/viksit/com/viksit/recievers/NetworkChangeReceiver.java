package pro.viksit.com.viksit.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import pro.viksit.com.viksit.assessment.database.AssessmentDataHandler;
import pro.viksit.com.viksit.assessment.pojo.AssessmentResultPojo;

/**
 * Created by Feroz on 18-04-2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if (!status.equalsIgnoreCase("Not connected to Internet")) {
            AssessmentDataHandler assessmentDataHandler = new AssessmentDataHandler(context);
            List<AssessmentResultPojo> assessmentResultPojoList =assessmentDataHandler.getAllContent();
            for(AssessmentResultPojo assessmentResultPojo:assessmentResultPojoList){
                System.out.println("assessmentResultPojo "+assessmentResultPojo.getDuration());
            }

        }
    }
}
