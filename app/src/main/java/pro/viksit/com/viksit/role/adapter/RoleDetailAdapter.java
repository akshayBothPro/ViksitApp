package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.role.pojo.Role;

/**
 * Created by Akshay on 18/03/2017.
 */

public class RoleDetailAdapter extends RecyclerView.Adapter<RoleDetailAdapter.MyViewHolder>  {

    private List<Role> roles;
    private Context context;

    private int screenWidth;
    private int screenHeight;

    public RoleDetailAdapter(List<Role> roles,Context context) {
        this.roles = roles;
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public RoleDetailAdapter(List<Role> roles,Context context, int screenWidth, int screenHeight) {
        this.roles = roles;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.role_details_card, parent, false);

        //
         return new MyViewHolder(itemView,viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Role role = roles.get(position);
        holder.title.setText(role.getTitle());
        Picasso.with(context).load(R.drawable.ic_4).transform(new CircleTransform()).into(holder.image);
        if(screenHeight != 0 && screenWidth != 0) {
            ViewGroup.LayoutParams params = holder.image.getLayoutParams();
            params.height = screenHeight/6;
            params.width = screenHeight/6;
            holder.image.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView title,label1,label2;
        public CardView cardview;
        public LinearLayout relativeLayout;
        public MyViewHolder(View view,int viewType) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
            relativeLayout = (LinearLayout) itemView.findViewById(R.id.label_layout);
            label1 = (TextView) itemView.findViewById(R.id.tv_label1);
            label2 = (TextView) itemView.findViewById(R.id.tv_label2);

        }

        @Override
        public void onClick(View view) {

        }

    }

}
