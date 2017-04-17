package pro.viksit.com.viksit.assessment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;
import pro.viksit.com.viksit.assessment.adapter.AssessmentTotalTimer;

/**
 * Created by Feroz on 17-04-2017.
 */

public class EndAssessmentFragment extends Fragment {
    private CountDownTimer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_end_assessment, container, false);
        Button submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AssessmentActivity)getActivity()).submitAssessment();
            }
        });
        return view;

    }




}
