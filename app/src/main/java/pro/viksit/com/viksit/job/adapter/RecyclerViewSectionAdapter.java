/*
package pro.viksit.com.viksit.job.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.pojo.Opening;

*/
/**
 * Created by Akshay on 22/03/2017.
 *//*



public class RecyclerViewSectionAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<Opening> allData;

    public RecyclerViewSectionAdapter(List<Opening> data) {

        this.allData = data;
    }


    @Override
    public int getSectionCount() {
        return allData.size();
    }

    @Override
    public int getItemCount(int section) {

        return allData.get(section).getAllItemsInSection().size();

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {

        String sectionName = allData.get(section).getHeaderTitle();
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.sectionTitle.setText(sectionName);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

        ArrayList<String> itemsInSection = allData.get(section).getAllItemsInSection();

        String itemName = itemsInSection.get(relativePosition);

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.itemTitle.setText(itemName);

        // Try to put a image . for sample i set background color in xml layout file
        // itemViewHolder.itemImage.setBackgroundColor(Color.parseColor("#01579b"));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        View v = null;
        if (header)

        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_section, parent, false);
            return new SectionViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.opening_job_card_item, parent, false);
            return new ItemViewHolder(v);
        }

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    // SectionViewHolder Class for Sections
    public static class SectionViewHolder extends RecyclerView.ViewHolder {


        final TextView sectionTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);

            sectionTitle = (TextView) itemView.findViewById(R.id.sectionTitle);


        }
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView title,subtitle,expiryDate;

        public ImageView itemImage,time;


        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_opening_item_title);
            subtitle = (TextView) itemView.findViewById(R.id.tv_opening_item_subtitle);
            expiryDate = (TextView) itemView.findViewById(R.id.tv_opening_item_status);
            itemImage = (ImageView) itemView.findViewById(R.id.iv_opening_item_image);
            time = (ImageView) itemView.findViewById(R.id.iv_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    */
/*Toast.makeText(v.getContext(), itemTitle.getText(), Toast.LENGTH_SHORT).show();*//*


                }
            });
        }
    }
}*/
