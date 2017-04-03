package pro.viksit.com.viksit.role.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

public class RolePurchaseActivity extends AppCompatActivity {
    private ImageButton back;
    private ImageView image;
    private TextView mainTitle;
    private TextView mainSubTitle;
    private TextView description;
    private RecyclerView skillsRecycler;
    private LinearLayout moduleListContainer;
    private RecyclerView faq;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_purchase);

        back = (ImageButton) findViewById(R.id.ibtn_back);
        image =(ImageView)findViewById(R.id.iv_role_purchase_image);
        mainTitle = (TextView) findViewById(R.id.tv_role_purchase_title);
        mainSubTitle =(TextView) findViewById(R.id.tv_role_purchase_subtitle);
        description = (TextView) findViewById(R.id.tv_role_purchase_desc);
        skillsRecycler = (RecyclerView) findViewById(R.id.rv_purchase_skills);
        moduleListContainer = (LinearLayout) findViewById(R.id.ll_role_purchase_module_list_con);
        faq = (RecyclerView) findViewById(R.id.rv_role_purchase_faq);
        submit = (Button) findViewById(R.id.btn_role_purchase_submit);
    }
}
