package pro.viksit.com.viksit.dashboard.adapter.profile;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.ProfileItem;

/**
 * Created by Akshay on 01/05/2017.
 */

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountRecyclerAdapter.ItemRowHolder> {

    private ArrayList<ProfileItem> dataList;
    private Context mContext;

    private int screenWidth;
    private int screenHeight;


    public AccountRecyclerAdapter(Context context, ArrayList<ProfileItem> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    public AccountRecyclerAdapter(Context context, ArrayList<ProfileItem> dataList, int screenWidth, int screenHeight) {
        this.dataList = dataList;
        this.mContext = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.account_sectioned_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeader();
        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();
        itemRowHolder.itemTitle.setText(sectionName);

        AccountSectionItemAdapter itemListDataAdapter = new AccountSectionItemAdapter(mContext, singleSectionItems, screenWidth, screenHeight);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
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

        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.tv_account_header);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);

        }

    }

}
