package pro.viksit.com.viksit.dashboard.fragment.profilefragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.profile.AccountRecyclerAdapter;
import pro.viksit.com.viksit.dashboard.pojo.ProfileData;
import pro.viksit.com.viksit.dashboard.pojo.ProfileItem;
import pro.viksit.com.viksit.role.activity.ModuleActivity;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;
import pro.viksit.com.viksit.util.CircleTransform;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    View view;
    Context context = this.getContext();

    private ImageView image;
    private ImageButton upload;
    private ProgressBar progress;
    private TextView percent;
    private RecyclerView recycler;
    private AccountRecyclerAdapter adapter;
    private ArrayList<ProfileItem> dataList;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);

        image = (ImageView) view.findViewById(R.id.iv_image);
        upload =(ImageButton) view.findViewById(R.id.ibtn_change_photo);
        progress = (ProgressBar) view.findViewById(R.id.pb_account_progress);
        percent = (TextView) view.findViewById(R.id.tv_account_progress);
        recycler = (RecyclerView) view.findViewById(R.id.rv_account_recycler);

        setdummydata();
        implementActions();

        return view;
    }

    public void implementActions(){
        //setting up vertical recycler view
        recycler.setHasFixedSize(true);
        adapter = new AccountRecyclerAdapter(context, dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);

        //setting circular image
        Picasso.with(getContext()).load(R.drawable.ic_disha).transform(new CircleTransform()).into(image);

    }

    public void setdummydata(){
        ProfileItem item;
        ProfileData data;
        dataList = new ArrayList<>();
        ArrayList <ProfileData> itemlist = new ArrayList<>();

        data = new ProfileData("Name", "akshay","edit");
        for(int i = 0 ; i < 30 ; i++) {
            itemlist.add(data);
        }
        item = new ProfileItem("Personal Details", itemlist);
        dataList.add(item);//for edit section

        itemlist = new ArrayList<>();
        data = new ProfileData("", "Recieve notifications for all responses to you questions ","Switch");
        for(int i = 0 ; i < 3 ; i++) {
            itemlist.add(data);
        }
        item = new ProfileItem("Notification Settings", itemlist);
        dataList.add(item);//for switch section
    }


}
