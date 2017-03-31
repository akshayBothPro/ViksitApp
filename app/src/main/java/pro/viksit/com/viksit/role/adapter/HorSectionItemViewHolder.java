package pro.viksit.com.viksit.role.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

/**
 * Created by Akshay on 30/03/2017.
 */


public class HorSectionItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView imageView;


    public HorSectionItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_role_description);
        /*imageView = (ImageView) itemView.findViewById(R.id.iv_recommended_role_item_image);*/
    }
    public void render(String titles, int imageResID){
        title.setText(titles);
        imageView.setImageResource(imageResID);
    }

}
