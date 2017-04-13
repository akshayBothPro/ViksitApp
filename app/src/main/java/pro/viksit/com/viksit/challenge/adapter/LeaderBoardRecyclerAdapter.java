package pro.viksit.com.viksit.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardProfile;

/**
 * Created by Akshay on 13/04/2017.
 */


public class LeaderBoardRecyclerAdapter extends RecyclerView.Adapter<LeaderBoardRecyclerAdapter.MyViewHolder>  {
    private ArrayList<LeaderBoardProfile> list;
    private Context context;

    public LeaderBoardRecyclerAdapter(ArrayList<LeaderBoardProfile> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_list_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LeaderBoardProfile profile = list.get(position);
        holder.name.setText(profile.getName());
        holder.rank.setText(profile.getRank());
        holder.xp.setText(profile.getXp());

        if(!profile.getImageURL().isEmpty()){
            Picasso.with(context).load(profile.getImageURL()).transform(new CircleTransform()).into(holder.image);
        } else if(profile.getImageResId() != 0){
            Picasso.with(context).load(profile.getImageResId()).transform(new CircleTransform()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView name, rank, xp;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.iv_item_image);
            name = (TextView) view.findViewById(R.id.tv_item_name);
            rank = (TextView) view.findViewById(R.id.tv_item_rank);
            xp = (TextView) view.findViewById(R.id.tv_item_xp);

        }

        @Override
        public void onClick(View view) {

        }

    }

}