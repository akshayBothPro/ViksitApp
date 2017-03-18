package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.Role;

public class ModuleViewActivity extends AppCompatActivity {
    private static final String TAG = ModuleViewActivity.class.getSimpleName();

    private TextView subtitle;
    private ImageView info;
    private Role role;
    private TextView currentXP;
    private TextView totalXP;
    private TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_view);

        role = (Role) getIntent().getSerializableExtra("role");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        subtitle = (TextView) findViewById(R.id.tv_moduleview_subtitle);
        info = (ImageView) findViewById(R.id.iv_moduleview_info);
        currentXP = (TextView) findViewById(R.id.tv_moduleview_currentXP);
        totalXP = (TextView) findViewById(R.id.tv_moduleview_totalXP);
        percent = (TextView) findViewById(R.id.tv_moduleview_percent);

        setSupportActionBar(toolbar);
        implmentActions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            System.out.println("back1 button clicked");
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void implmentActions(){
        subtitle.setText(role.getSubtitle());
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                System.out.println("info clciked");
            }
        });
        currentXP.setText(Html.fromHtml("<b>" + Integer.toString(role.getCompletedItems()) +"</b> XP"));
        totalXP.setText("of " + Integer.toString(role.getTotalItems()) + "XP earned");
        int n = (role.getCompletedItems()*100)/role.getTotalItems();
        percent.setText(Html.fromHtml("<b>" + Integer.toString(n) +"</b> %"));


    }

}
