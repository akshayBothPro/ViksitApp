package pro.viksit.com.viksit.assessment.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.adapter.QuestionsRecyclerViewAdapter;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class QuestionsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private QuestionsRecyclerViewAdapter qAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_questions);
        fab = (FloatingActionButton) findViewById(R.id.fab_ques);

        setSupportActionBar(toolbar);
    }

    private void implementActions(){
        qAdapter = new QuestionsRecyclerViewAdapter(getBaseContext(), appliedJobs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(qAdapter);
        qAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Module position: " + position);
                        /*Intent intent = new Intent(RoleActivity.this, ModuleViewActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);*/
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );
    }
}
