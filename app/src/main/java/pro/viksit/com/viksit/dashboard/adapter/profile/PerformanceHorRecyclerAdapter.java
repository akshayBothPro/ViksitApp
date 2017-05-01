package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dummy.pojo.SkillReportPOJO;

/**
 * Created by Akshay on 02/05/2017.
 */

public class PerformanceHorRecyclerAdapter extends RecyclerView.Adapter<PerformanceHorRecyclerAdapter.SingleItemRowHolder> {

    private ArrayList<SkillReportPOJO> itemsList;
    private Context mContext;

    private int screenWidth;
    private int screenHeight;

    public PerformanceHorRecyclerAdapter(Context context, ArrayList<SkillReportPOJO> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    public PerformanceHorRecyclerAdapter(Context context, ArrayList<SkillReportPOJO> itemsList, int screenWidth, int screenHeight) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.performance_hor_list_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SkillReportPOJO singleItem = itemsList.get(i);
        holder.tvTitle.setText(singleItem.getName());
        /*holder.itemImage.setBackgroundResource(singleItem.get());*/

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView itemImage;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tv_item_title);
            this.itemImage = (ImageView) view.findViewById(R.id.iv_item_image);

            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

}