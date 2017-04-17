package pro.viksit.com.viksit.dashboard.adapter;

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
import pro.viksit.com.viksit.util.CircleTransform;
import pro.viksit.com.viksit.dashboard.pojo.Notification;

/**
 * Created by Akshay on 06/04/2017.
 */

public class NoticationVerticalRecyclerAdapter extends RecyclerView.Adapter<NoticationVerticalRecyclerAdapter.MyViewHolder>  {
    private ArrayList<Notification> notificationList;
    private Context context;

    public NoticationVerticalRecyclerAdapter(ArrayList<Notification> notificationList,Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.title.setText(notification.getInfo());
        holder.time.setText(notification.getTime());

        if(notification.getImageResID() != 0){
            Picasso.with(context).load(notification.getImageResID()).transform(new CircleTransform()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView title, time;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.iv_notification_image);
            title = (TextView) view.findViewById(R.id.tv_notification_desc);
            time = (TextView) view.findViewById(R.id.tv_notification_time);

        }

        @Override
        public void onClick(View view) {

        }

    }

}