package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.role.pojo.Role;

/**
 * Created by Akshay on 02/04/2017.
 */

public class ModuleHorizontalSectionListAdapter extends RecyclerView.Adapter<ModuleHorizontalSectionListAdapter.SingleItemRowHolder> {

    private ArrayList<Role> itemsList;
    private Context mContext;

    public ModuleHorizontalSectionListAdapter(Context context, ArrayList<Role> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Role singleItem = itemsList.get(i);

        holder.tvTitle.setText(singleItem.getTitle());
        holder.itemImage.setImageResource(singleItem.getImageResID());

        Picasso.with(mContext).load(singleItem.getImageResID()).transform(new CircleTransform()).into(holder.itemImage);

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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

}