package pro.viksit.com.viksit.challenge.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pro.viksit.com.viksit.challenge.activity.LeaderBoardActivity;
import pro.viksit.com.viksit.challenge.adapter.LeaderBoardRecyclerAdapter;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardCourse;

/**
 * Created by Akshay on 17/04/2017.
 */

public class LeaderBoardAsync extends AsyncTask<String, Integer, String> {

    private Context context;
    private RecyclerView recycler;
    private LeaderBoardActivity activity;
    private ArrayList<LeaderBoardCourse> list;
    private LeaderBoardRecyclerAdapter adapter;




    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        /*progressBar.setVisibility(View.VISIBLE);
        error_layout.setVisibility(View.GONE);*/
    }

}
