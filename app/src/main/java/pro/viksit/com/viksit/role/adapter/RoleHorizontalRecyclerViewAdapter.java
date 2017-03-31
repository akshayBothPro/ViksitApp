package pro.viksit.com.viksit.role.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.RecommendedRole;

/**
 * Created by Akshay on 18/03/2017.
 */

public class RoleHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RoleHorizontalRecyclerViewAdapter.MyViewHolder>  {
    private List<RecommendedRole> recommendedRoles;

    public RoleHorizontalRecyclerViewAdapter(List<RecommendedRole> recommendedRoles) {
        this.recommendedRoles = recommendedRoles;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_detail_hor_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecommendedRole recommendedRole = recommendedRoles.get(position);
        holder.image.setImageResource(recommendedRole.getResID());
        holder.description.setText(recommendedRole.getDescription());

        // if we have image URL instead of resID use it
        /*URL url = recommendedRole.getImageURL();
        Bitmap b/opolp;'///'00 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        holder.image.setImageBitmap(bmp);*/
    }

    @Override
    public int getItemCount() {
        return recommendedRoles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView image;
        public TextView description;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.iv_job_item_image);
            description = (TextView)view.findViewById(R.id.tv_job_item_title);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("recommended roles image clicked");
        }

    }

}