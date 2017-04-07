package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.SectionedRole;

/**
 * Created by Akshay on 02/04/2017.
 */

public class ModuleHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<ModuleHorizontalRecyclerViewAdapter.ItemRowHolder> {

    private ArrayList<SectionedRole> dataList;
    private Context mContext;

    private int screenWidth;
    private int screenHeight;

    public ModuleHorizontalRecyclerViewAdapter(Context context, ArrayList<SectionedRole> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    public ModuleHorizontalRecyclerViewAdapter(Context context, ArrayList<SectionedRole> dataList, int screenWidth, int screenHeight) {
        this.dataList = dataList;
        this.mContext = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.role_horizontal_item_card_view, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();
        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(sectionName);
        ModuleHorizontalSectionListAdapter itemListDataAdapter = new ModuleHorizontalSectionListAdapter(mContext, singleSectionItems, screenWidth, screenHeight);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

        itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);

    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        public TextView itemTitle;
        public RecyclerView recycler_view_list;
       /* protected Button btnMore;*/

        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.tv_role_description);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            /*this.btnMore= (Button) view.findViewById(R.id.btnMore);*/
        }

    }

}