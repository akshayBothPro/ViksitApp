package pro.viksit.com.viksit.dashboard.adapter.profile;

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
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.ParentSkill;
import pro.viksit.com.viksit.dummy.pojo.SkillReportPOJO;
import pro.viksit.com.viksit.util.CircleTransform;
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.util.SaveImageAsync;

/**
 * Created by Akshay on 02/05/2017.
 */

public class PerformanceHorRecyclerAdapter extends RecyclerView.Adapter<PerformanceHorRecyclerAdapter.SingleItemRowHolder> {

    private ArrayList<SkillReportPOJO> itemsList;
    private Context mContext;
    private RecyclerView expandableRecycler;

    public PerformanceHorRecyclerAdapter(Context context, ArrayList<SkillReportPOJO> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    public PerformanceHorRecyclerAdapter(Context context, ArrayList<SkillReportPOJO> itemsList, RecyclerView expandableRecycler) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.expandableRecycler = expandableRecycler;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.performance_hor_list_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {

        SkillReportPOJO singleItem = itemsList.get(position);
        holder.tvTitle.setText(singleItem.getName());
        String url = singleItem.getImageURL();

        if (!url.startsWith("http")) {
            url += mContext.getResources().getString(R.string.resourceserverip) + url;
        }
        int index = url.lastIndexOf("/");
        ImageSaver image = new ImageSaver(this.mContext).
                setParentDirectoryName("skills").
                setFileName(new DisplayUtil().getFileNameReplaced(url.substring(index + 1, url.length()))).
                setExternal(ImageSaver.isExternalStorageReadable());
        if (image.checkFile()) {
            Picasso.with(mContext).load(image.pathFile()).transform(new CircleTransform()).into(holder.itemImage);
        } else {
            Picasso.with(mContext).load(url).transform(new CircleTransform()).into(holder.itemImage);//image
            new SaveImageAsync(image).execute(url);
        }

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
                    int position = getAdapterPosition();
                    //changing expandable on selection of item
                    List<ParentSkill> parentSkills = new ArrayList<>();
                    parentSkills = itemsList.get(position).getParentSkills();
                    PerformanceVerExpandableAdapter adapter = new PerformanceVerExpandableAdapter(mContext, parentSkills);
                    expandableRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            });

        }

    }

}