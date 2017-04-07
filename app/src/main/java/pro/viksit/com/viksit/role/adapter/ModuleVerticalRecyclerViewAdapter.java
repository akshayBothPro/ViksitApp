package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
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

import com.squareup.picasso.Picasso;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.role.pojo.Role;

/**
 * Created by Akshay on 02/04/2017.
 */

public class ModuleVerticalRecyclerViewAdapter extends RecyclerView.Adapter<ModuleVerticalRecyclerViewAdapter.MyViewHolder>  {
    private List<Role> roles;
    private Context context;

    private int screenWidth;
    private int screenHeight;

    public ModuleVerticalRecyclerViewAdapter(List<Role> roles,Context context) {
        this.roles = roles;
        this.context = context;
    }

    public ModuleVerticalRecyclerViewAdapter(List<Role> roles,Context context, int screenWidth, int screenHeight) {
        this.roles = roles;
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_vertical_card_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Role role = roles.get(position);
        holder.title.setText(role.getTitle());
        holder.subtitle.setText(role.getSubtitle());
        holder.status.setText(Integer.toString(role.getCompletedItems()) + " of " + Integer.toString(role.getTotalItems()) + " modules completed");
        /*holder.image.setImageResource(role.getImageResID());*/
        holder.progressBar.setMax(role.getTotalItems());
        holder.progressBar.setProgress(role.getCompletedItems());
        holder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_drawable));
        /*holder.progressBar.getdeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.linkedIn_color), PorterDuff.Mode.MULTIPLY);*/
        /*holder.cardview.setPadding(20,20,20,20);*/
        Picasso.with(context).load(role.getImageResID()).transform(new CircleTransform()).into(holder.image);
        if(screenHeight != 0 && screenWidth != 0) {
            ViewGroup.LayoutParams params = holder.image.getLayoutParams();
            params.height = screenHeight/7;
            params.width = screenHeight/6;
            holder.image.setLayoutParams(params);
        }
        /*Picasso.with(context).load(role.getImageURL()).transform(new CircleTransform()).into(holder.image);*/
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

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_role_vertical_item_title);
            subtitle = (TextView) view.findViewById(R.id.tv_role_vertical_item_subtitle);
            status = (TextView) view.findViewById(R.id.tv_role_vertical_item_status);
            image = (ImageView)view.findViewById(R.id.iv_role_vertical_item_image);
            progressBar = (ProgressBar) view.findViewById(R.id.pb_role_vertical_item_progress);

            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#5bc6f6"), PorterDuff.Mode.MULTIPLY);

        }

        @Override
        public void onClick(View view) {

        }

    }

}
