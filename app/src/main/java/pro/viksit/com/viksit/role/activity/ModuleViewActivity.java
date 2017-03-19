package pro.viksit.com.viksit.role.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.util.HexagonImageView;
import pro.viksit.com.viksit.role.adapter.ModuleViewRecyclerAdapter;
import pro.viksit.com.viksit.role.pojo.Module;
import pro.viksit.com.viksit.role.pojo.Role;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class ModuleViewActivity extends AppCompatActivity {
    private static final String TAG = ModuleViewActivity.class.getSimpleName();

    private TextView subtitle;
    private ImageView info;
    private Role role;
    private TextView currentXP;
    private TextView totalXP;
    private TextView percent;
    private HexagonImageView hexagonImageView;
    private RecyclerView recyclerView;
    private ModuleViewRecyclerAdapter adapter;
    private List<Module> modules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_view);

        role = (Role) getIntent().getSerializableExtra("role");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_accessible_black_36dp);
        subtitle = (TextView) findViewById(R.id.tv_moduleview_subtitle);
        info = (ImageView) findViewById(R.id.iv_moduleview_info);
        currentXP = (TextView) findViewById(R.id.tv_moduleview_currentXP);
        totalXP = (TextView) findViewById(R.id.tv_moduleview_totalXP);
        percent = (TextView) findViewById(R.id.tv_moduleview_percent);
        hexagonImageView = (HexagonImageView) findViewById(R.id.profile_pic);
        recyclerView = (RecyclerView) findViewById(R.id.rv_moduleview_recycler);

        setSupportActionBar(toolbar);
        setModuleViewData();
        implementActionsListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //Log.i("Akshay", "back1 button clicked");
            System.out.println("back1 button clicked");
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void implementActionsListeners(){
        subtitle.setText(role.getSubtitle());
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                System.out.println("info clciked");
            }
        });
        currentXP.setText(Html.fromHtml("<b>" + Integer.toString(role.getCompletedItems()) +" <small>XP</small></b>"));
        totalXP.setText(Html.fromHtml("of <b>" + Integer.toString(role.getTotalItems()) + " <small>XP</small></b> earned"));
        int n = (role.getCompletedItems()*100)/role.getTotalItems();
        percent.setText(Html.fromHtml("<b>" + Integer.toString(n) +" <small>%</small></b>"));

        // setting up vertical recycler view
        adapter = new ModuleViewRecyclerAdapter(modules,getBaseContext());
        RecyclerView.LayoutManager vLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(vLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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

    private void setModuleViewData(){
        modules = new ArrayList<>();

        //1st data entry
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Risk Analysis");
        labels.add("Risk Management");
        //Module constructor => (int imageResID, String moduleTitle, ArrayList<String> labels)
        Module module = new Module(R.mipmap.ic_adjust_black_24dp, "Selling Circus Funds", labels);
        modules.add(module);

        //2nd data entry
        labels = new ArrayList<>();
        labels.add("Risk Analysis Profiling");
        labels.add("Risk Management");
        module = new Module(R.mipmap.ic_tag_faces_black_24dp, "Buying Mutual circus", labels);
        modules.add(module);

        //3rd data entry
        labels = new ArrayList<>();
        labels.add("Customer Profiling");
        labels.add("Risk Management");
        module = new Module(R.mipmap.ic_monetization_on_black_24dp, "Selling Mutual Funds", labels);
        modules.add(module);

        //4th data entry
        labels = new ArrayList<>();
        labels.add("Risk Analysis");
        labels.add("Risk Management Profiling");
        module = new Module(R.mipmap.ic_assignment_black_24dp, "Buying Circus Funds", labels);
        modules.add(module);

        //5th data entry
        labels = new ArrayList<>();
        labels.add("Customer Profiling");
        labels.add("Risk Management");
        module = new Module(R.mipmap.ic_adjust_black_24dp, "Selling Mutual Circus", labels);
        modules.add(module);

        //6th data entry
        labels = new ArrayList<>();
        labels.add("Risk Analysis");
        labels.add("Risk Management Profiling");
        module = new Module(R.mipmap.ic_event_note_black_24dp, "Buying Mutual Funds", labels);
        modules.add(module);
    }
}
