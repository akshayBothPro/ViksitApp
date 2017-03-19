package pro.viksit.com.viksit.role.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.Role;

/**
 * Created by Akshay on 18/03/2017.
 */

public class RoleVerticalRecyclerViewAdapter extends RecyclerView.Adapter<RoleVerticalRecyclerViewAdapter.MyViewHolder>  {
    private List<Role> roles;

    public RoleVerticalRecyclerViewAdapter(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.role_vertical_item_card_view, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Role role = roles.get(position);
        holder.title.setText(role.getTitle());
        holder.subtitle.setText(role.getSubtitle());
        holder.status.setText(Integer.toString(role.getCompletedItems()) + " of " + Integer.toString(role.getTotalItems()) + " items completed");
        holder.image.setImageResource(role.getImageResID());
        holder.progressBar.setMax(role.getTotalItems());
        holder.progressBar.setProgress(role.getCompletedItems());

        // if we have image URL instead of resID use it
        /*URL url = role.getImageURL();
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        holder.image.setImageBitmap(bmp);*/
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView title, subtitle, status;
        public ProgressBar progressBar;
        public CardView cardview;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_role_vertical_item_title);
            subtitle = (TextView) view.findViewById(R.id.tv_role_vertical_item_subtitle);
            status = (TextView) view.findViewById(R.id.tv_role_vertical_item_status);
            image = (ImageView)view.findViewById(R.id.iv_role_vertical_item_image);
            progressBar = (ProgressBar) view.findViewById(R.id.pb_role_vertical_item_progress);
            cardview = (CardView)view.findViewById(R.id.cv_role_vertical_card);

            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#5bc6f6"), PorterDuff.Mode.MULTIPLY);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }

}
