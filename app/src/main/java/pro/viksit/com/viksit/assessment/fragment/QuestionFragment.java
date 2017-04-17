package pro.viksit.com.viksit.assessment.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;
import pro.viksit.com.viksit.assessment.pojo.Option;
import pro.viksit.com.viksit.assessment.pojo.OptionPOJO;
import pro.viksit.com.viksit.assessment.pojo.Question;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;
import pro.viksit.com.viksit.assessment.pojo.QuestionResult;

/**
 * Created by Feroz on 20-03-2017.
 */

public class QuestionFragment extends Fragment {
    public static final String GET_QUESTION = "GET_QUESTION";
    public static final String POSITION = "POSITION";
    public static final String TOTALCOUNT = "TOTALCOUNT";

    private TextView header,hiddentext;
    private WebView question_title;
    private LinearLayout button_layout;
    private ArrayList<RelativeLayout> optionWebviewList;
    private ArrayList<WebView> webViewArrayList;
    private QuestionPOJO questionPOJO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.question_fragment, container, false);
        header = (TextView) view.findViewById(R.id.header);
        question_title = (WebView) view.findViewById(R.id.question_title);
        button_layout = (LinearLayout) view.findViewById(R.id.button_layout);
        questionPOJO = (QuestionPOJO) getArguments().getSerializable(GET_QUESTION);
        header.setText("Question "+(getArguments().getInt(POSITION)+1)+" OF "+getArguments().getInt(TOTALCOUNT));
        hiddentext = (TextView) view.findViewById(R.id.hiddentext);
        hiddentext.setText(questionPOJO.getDurationInSec()+"");
        question_title.loadDataWithBaseURL(null, questionPOJO.getText(), "text/html", "utf-8", null);
        question_title.setBackgroundColor(0);
        optionWebviewList = new ArrayList<>();
        webViewArrayList = new ArrayList<>();
        for(final OptionPOJO option: questionPOJO.getOptions()){
            LinearLayout.LayoutParams mainparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mainparams.setMargins(10,10,10,10);

            final RelativeLayout linearLayout = new RelativeLayout(getContext());
            linearLayout.setLayoutParams(mainparams);
            linearLayout.requestLayout();

            final WebView optionview = new WebView(getContext());
            optionview.getSettings().setDomStorageEnabled(true);
            optionview.getSettings().setSaveFormData(true);
            optionview.getSettings().setAllowContentAccess(true);
            optionview.getSettings().setAllowFileAccess(true);
            optionview.getSettings().setAllowFileAccessFromFileURLs(true);
            optionview.getSettings().setAllowUniversalAccessFromFileURLs(true);
            optionview.setWebViewClient(new WebViewClient());
            optionview.setClickable(true);
            optionview.getSettings().setJavaScriptEnabled(true);
            optionview.setBackgroundColor(0);
            optionview.setWebChromeClient(new WebChromeClient());

            if(((AssessmentActivity)getActivity()).checkSelectedOption(option.getId()))
                linearLayout.setBackground(getResources().getDrawable(R.drawable.select_option_bg));
            else
            linearLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));
            optionview.loadDataWithBaseURL(null, option.getText(), "text/html", "utf-8", null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,5,5,5);

            optionview.setLayoutParams(params);




            optionview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            removeColor();
                            optionview.loadUrl(
                                    "javascript:document.body.style.setProperty(\"color\", \"#0288d1\");"
                            );
                            linearLayout.setBackground(getResources().getDrawable(R.drawable.select_option_bg));
                            if(((AssessmentActivity)getActivity()).lockableViewPager.getCurrentItem() != ((AssessmentActivity)getActivity()).lockableViewPager.getAdapter().getCount()-1){
                                ((AssessmentActivity)getActivity()).setResult(questionPOJO.getId(),questionPOJO.getId(),((AssessmentActivity)getActivity()).question_time_taken);
                                int position = ((AssessmentActivity)getActivity()).lockableViewPager.getCurrentItem();
                                ((AssessmentActivity)getActivity()).lockableViewPager.setCurrentItem(((AssessmentActivity)getActivity()).lockableViewPager.getCurrentItem()+1);
                                ((AssessmentActivity)getActivity()).checkRecylclerIconChange(position,questionPOJO.getId());
                            }else{
                                System.out.println("Assessment ENded here");
                                ((AssessmentActivity)getActivity()).printResult();

                            }



                            break;
                    }

                    return false;
                }


            });
            linearLayout.addView(optionview);
            optionWebviewList.add(linearLayout);
            webViewArrayList.add(optionview);
            button_layout.addView(linearLayout);



        }



        return view;
    }

    private void removeColor() {
        if(optionWebviewList != null){
            for(RelativeLayout webView:optionWebviewList){
                webView.setBackgroundColor(getResources().getColor(R.color.white));
                webView.setBackground(getResources().getDrawable(R.drawable.button_bg));
            }
            for(WebView webView:webViewArrayList){
                webView.loadUrl(
                        "javascript:document.body.style.setProperty(\"color\", \"#000000\");"
                );
            }

        }
    }
}
