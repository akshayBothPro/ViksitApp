package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.Module;
import pro.viksit.com.viksit.role.pojo.Role;

/**
 * Created by Akshay on 19/03/2017.
 */


public class ModuleViewRecyclerAdapter extends RecyclerView.Adapter<ModuleViewRecyclerAdapter.MyViewHolder>  {
    private List<Module> modules;
    private Context context;

    public ModuleViewRecyclerAdapter(List<Module> modules, Context context) {
        this.modules = modules;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.moduleview_item_card, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Module module = modules.get(position);
        holder.title.setText(module.getModuleTitle());
        holder.image.setImageResource(module.getImageResID());


        //for text labels
        /*for( String label : module.getLabels() )
        {
            TextView textView = new TextView(context);
            textView.setText(label);
            textView.setTextSize(12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxLines(1);
            textView.setBackgroundResource(R.drawable.moduleview_item_texel_label);

            holder.labelContainer.addView(textView);
        }*/
        // if we have image URL instead of resID use it
        /*URL url = role.getImageURL();
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        holder.image.setImageBitmap(bmp);*/
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView title;
        public CardView cardview;
        public LinearLayout labelContainer;
        public TextView label1;
        public TextView label2;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_moduleview_item_title);
            image = (ImageView)view.findViewById(R.id.iv_moduleview_item_image);
            cardview = (CardView)view.findViewById(R.id.cv_moduleview_card);
            labelContainer = (LinearLayout) view.findViewById(R.id.ll_moduleview_item_label_container);

            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }

}
