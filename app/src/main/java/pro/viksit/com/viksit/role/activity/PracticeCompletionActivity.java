package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.adapter.PracticeCompletionAdapter;
import pro.viksit.com.viksit.role.pojo.Practice;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class PracticeCompletionActivity extends AppCompatActivity {

    private TextView title,subtitle;
    private ImageView tick;
    private RecyclerView verticalRecycler;
    private PracticeCompletionAdapter adapter;
    private ArrayList<Practice> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_completion);

        title = (TextView) findViewById(R.id.tv_practice_title);
        subtitle = (TextView) findViewById(R.id.tv_practice_subtitle);
        tick = (ImageView) findViewById(R.id.iv_practice_tick);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_practice_completed);

        list = setDummyData(list);



    }

    public void implementActions(){

        //setting vertical recyclerview
        verticalRecycler.setHasFixedSize(true);
        adapter = new PracticeCompletionAdapter(list,getBaseContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(adapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(PracticeCompletionActivity.this, CheckoutActivity.class);
                       /* Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);*/
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );

    }

    public ArrayList<Practice> setDummyData(ArrayList<Practice> list){
        list = new ArrayList<>();
        Practice item;

        for(int i = 0 ; i < 5 ; i++) {
            item = new Practice("Introduction to risk", 100, 50);
            list.add(item);

            item = new Practice("Conceptualization of marvels", 100, 90);
            list.add(item);
        }
        return list;
    }
}
