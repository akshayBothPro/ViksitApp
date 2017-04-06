package pro.viksit.com.viksit.dashboard.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.NoticationVerticalRecyclerAdapter;
import pro.viksit.com.viksit.dashboard.pojo.Notification;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class NotificationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button backBtn;
    private TextView title;
    private RecyclerView verticalRecycler;
    private NoticationVerticalRecyclerAdapter adapter;
    private ArrayList<Notification> notificationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        backBtn = (Button) findViewById(R.id.btn_notfication_back);
        title = (TextView) findViewById(R.id.tv_notification_toolbar_title);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_notification);


        setSupportActionBar(toolbar);
        setNotificationData();
        implementActions();
    }

    private void implementActions(){
        // setting up vertical recycler view
        adapter = new NoticationVerticalRecyclerAdapter(notificationArrayList,getBaseContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(adapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(NotificationActivity.this, DashboardActivity.class);
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

    private void setNotificationData(){
        notificationArrayList = new ArrayList<>();
        Notification notification;

        for(int i = 0 ; i < 5 ; i++) {
            notification = new Notification(R.drawable.ic_1, "Sunil has challenge you", "4 hours ago");
            notificationArrayList.add(notification);

            notification = new Notification(R.drawable.ic_2, "Ajith has challenge you", "4 hours ago");
            notificationArrayList.add(notification);

            notification = new Notification(R.drawable.ic_5, "Deepak has challenge you", "4 hours ago");
            notificationArrayList.add(notification);
        }
    }
}
