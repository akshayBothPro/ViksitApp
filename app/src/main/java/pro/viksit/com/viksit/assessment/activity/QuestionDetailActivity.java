package pro.viksit.com.viksit.assessment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.adapter.CommentsAdapter;
import pro.viksit.com.viksit.assessment.pojo.Comment;

public class QuestionDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView back;
    private CircularImageView circularImageView;
    private TextView asks;
    private WebView webView;
    private Button commentCount;
    private RecyclerView commentRecycler;
    private CommentsAdapter commentsAdapter;
    private ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        back = (ImageView) findViewById(R.id.iv_back_btn);
        circularImageView = (CircularImageView) findViewById(R.id.iv_circle_image);
        asks =(TextView) findViewById(R.id.tv_toolbar_title);
        webView = (WebView) findViewById(R.id.question_webview);
        commentCount = (Button) findViewById(R.id.comment_counts);
        commentRecycler = (RecyclerView) findViewById(R.id.rv_comments);

        setSupportActionBar(toolbar);
        prepareDummyCommentData();
        implementActions();
    }

    private void implementActions(){
        commentsAdapter = new CommentsAdapter(getBaseContext(),comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        commentRecycler.setLayoutManager(linearLayoutManager);
        commentRecycler.setItemAnimator(new DefaultItemAnimator());
        commentRecycler.setAdapter(commentsAdapter);
        commentsAdapter.notifyDataSetChanged();

        commentCount.setText(comments.size());
    }

    private void prepareDummyCommentData(){
        comments = new ArrayList<>();
        Comment comment;
        // Comment constructor => (String commentor, String time, String commentText, int imageResID)
        for(int i = 1 ; i < 5; i++) {
            comment = new Comment("Amitabh Bacchan","23 hours ago","are the individuals with the most views on their questions in the statistics page",R.drawable.profile_default);
            comments.add(comment);

            comment = new Comment("Karthik Shetty","19 hours ago","are the people with the worst views on their questions in the analytics page",R.drawable.profile_default);
            comments.add(comment);

            comment = new Comment("Vinay Sharma","15 hours ago","are the adults with the best views on their answers in the statistics page",R.drawable.profile_default);
            comments.add(comment);
        }

    }
}
