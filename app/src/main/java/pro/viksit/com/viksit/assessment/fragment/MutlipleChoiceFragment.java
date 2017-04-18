package pro.viksit.com.viksit.assessment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;
import pro.viksit.com.viksit.assessment.pojo.OptionPOJO;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;

/**
 * Created by Feroz on 18-04-2017.
 */

public class MutlipleChoiceFragment extends Fragment {
    public static final String GET_QUESTION = "GET_QUESTION";
    public static final String POSITION = "POSITION";
    public static final String TOTALCOUNT = "TOTALCOUNT";

    private TextView header,hiddentext;
    private WebView question_title;
    private LinearLayout button_layout;
    private HashMap<Integer,RelativeLayout> optionWebviewList;
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
        question_title.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        String head = "<head>" +
                "<style>body {font-family: 'Roboto', sans-serif; src: url('file:///android_asset/fonts/Roboto-Medium.ttf');color:black;}</style></head>";
        String htmlData= "<html>"+head+"<body>"+questionPOJO.getText()+"</body></html>" ;
        System.out.println(htmlData);
        question_title.loadDataWithBaseURL("file:///android_asset/",htmlData , "text/html", "utf-8", null);
        question_title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        question_title.setLongClickable(false);
        question_title.setHapticFeedbackEnabled(false);

        question_title.setBackgroundColor(0);
        optionWebviewList = new HashMap<>();
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
            optionview.getSettings().setStandardFontFamily("Roboto-Medium");
            if(((AssessmentActivity)getActivity()).checkSelectedOption(option.getId()))
                linearLayout.setBackground(getResources().getDrawable(R.drawable.select_option_bg));
            else
                linearLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));


            optionview.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
            optionview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            optionview.setLongClickable(false);
            optionview.setHapticFeedbackEnabled(false);
            String optionhead = "<head>" +
                    "<style>body {font-family: 'Roboto', sans-serif; src: url('file:///android_asset/fonts/Roboto-Medium.ttf');color:black;}</style></head>";
            String optionhtmlData= "<html>"+optionhead+"<body>"+option.getText()+"</body></html>" ;
            optionview.loadDataWithBaseURL("file:///android_asset/", optionhtmlData, "text/html", "utf-8", null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,5,5,5);

            optionview.setLayoutParams(params);




            optionview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:


                                if(linearLayout.getBackground().getConstantState().equals(getContext().getResources().getDrawable(R.drawable.select_option_bg).getConstantState())) {
                                    linearLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));

                                    List<Integer> options_id = new ArrayList<>();
                                    for(Integer key:optionWebviewList.keySet()){
                                        if(optionWebviewList.get(key).getBackground().getConstantState().equals(getContext().getResources().getDrawable(R.drawable.select_option_bg).getConstantState())){
                                            options_id.add(key);
                                        }
                                    }
                                    boolean flag = options_id.size()>0;
                                    ((AssessmentActivity) getActivity()).removeResult(questionPOJO.getId(), option.getId(),flag);


                                    optionview.loadUrl(
                                            "javascript:document.body.style.setProperty(\"color\", \"#000000\");"
                                    );
                                }else {
                                    optionview.loadUrl(
                                            "javascript:document.body.style.setProperty(\"color\", \"#0288d1\");"
                                    );
                                    linearLayout.setBackground(getResources().getDrawable(R.drawable.select_option_bg));
                                    if (((AssessmentActivity) getActivity()).lockableViewPager.getCurrentItem() != ((AssessmentActivity) getActivity()).lockableViewPager.getAdapter().getCount() - 1) {
                                        ((AssessmentActivity) getActivity()).setResultMultiChoice(questionPOJO.getId(), option.getId(), ((AssessmentActivity) getActivity()).question_time_taken);
                                        int position = ((AssessmentActivity) getActivity()).lockableViewPager.getCurrentItem();
                                        // ((AssessmentActivity)getActivity()).lockableViewPager.setCurrentItem(((AssessmentActivity)getActivity()).lockableViewPager.getCurrentItem()+1);
                                        ((AssessmentActivity) getActivity()).checkRecylclerIconChange(position, questionPOJO.getId());
                                    } else {
                                        System.out.println("Assessment ENded here");
                                        ((AssessmentActivity) getActivity()).printResult();

                                    }

                                }

                            break;
                    }

                    return false;
                }


            });
            linearLayout.addView(optionview);
            optionWebviewList.put(option.getId(),linearLayout);
            webViewArrayList.add(optionview);
            button_layout.addView(linearLayout);
        }

        return view;
    }



}
