package pro.viksit.com.viksit.assessment.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;
import pro.viksit.com.viksit.assessment.pojo.Option;
import pro.viksit.com.viksit.assessment.pojo.Question;

/**
 * Created by Feroz on 20-03-2017.
 */

public class QuestionFragment extends Fragment {
    public static final String GET_QUESTION = "GET_QUESTION";
    private TextView header,question_title;
    private RelativeLayout button_layout;
    private int count;
    private ArrayList<Button> buttonArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.question_fragment, container, false);
        header = (TextView) view.findViewById(R.id.header);
        question_title = (TextView) view.findViewById(R.id.question_title);
        button_layout = (RelativeLayout) view.findViewById(R.id.button_layout);
        count = 1;
        Question question = (Question) getArguments().getSerializable(GET_QUESTION);
        question_title.setText(question.getText());
        buttonArrayList = new ArrayList<>();
        for(Option option: question.getOptionArrayList()){
            final Button button = new Button(getContext());
            button.setId(count);
            button.setBackground(getResources().getDrawable(R.drawable.button_bg));
            button.setTextColor(Color.parseColor("#767676"));
            button.setText(option.getText());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(count != 1){
                params.addRule(RelativeLayout.BELOW,count-1);
            }else{
            }
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(10,10,10,10);
            button.setLayoutParams(params);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeColor();
                    button.setBackgroundColor(getResources().getColor(R.color.theme_color));
                    AssessmentActivity.changeCheckbutton();
                }
            });
            buttonArrayList.add(button);
            button_layout.addView(button);
            count++;
        }
        count = 1;



        return view;
    }

    private void removeColor() {
        if(buttonArrayList != null){
            for(Button button:buttonArrayList){
                button.setBackgroundColor(getResources().getColor(R.color.white));
                button.setBackground(getResources().getDrawable(R.drawable.button_bg));
            }
        }
    }
}
