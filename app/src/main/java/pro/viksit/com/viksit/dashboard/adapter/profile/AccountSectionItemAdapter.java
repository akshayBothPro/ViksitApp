package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.ProfileData;

/**
 * Created by Akshay on 01/05/2017.
 */


public class AccountSectionItemAdapter extends RecyclerView.Adapter<AccountSectionItemAdapter.SingleItemRowHolder> {

    private ArrayList<ProfileData> itemsList;
    private Context mContext;

    private int screenWidth;
    private int screenHeight;

    public AccountSectionItemAdapter(Context context, ArrayList<ProfileData> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    public AccountSectionItemAdapter(Context context, ArrayList<ProfileData> itemsList, int screenWidth, int screenHeight) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.account_list_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        ProfileData singleItem = itemsList.get(i);
        holder.title.setText(singleItem.getTitle());
        holder.value.setText(singleItem.getValue());

        if(singleItem.getType().equalsIgnoreCase("Switch")){
            holder.switch1.setVisibility(View.VISIBLE);
            holder.value.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
            holder.edit.setVisibility(View.GONE);
        } else {
            holder.switch1.setVisibility(View.GONE);
            holder.value.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.VISIBLE);
            holder.edit.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        private TextView title,value;
        private ImageButton edit;
        private SwitchCompat switch1;


        public SingleItemRowHolder(View view) {
            super(view);

            this.title = (TextView) view.findViewById(R.id.tv_item_title);
            this.value = (TextView) view.findViewById(R.id.tv_item_value);
            this.edit = (ImageButton) view.findViewById(R.id.ibtn_edit);
            this.switch1 = (SwitchCompat) view.findViewById(R.id.switch1);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), value.getText(), Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

}