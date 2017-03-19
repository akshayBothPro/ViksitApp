package pro.viksit.com.viksit.job.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import pro.viksit.com.viksit.R;

/**
 * Created by Akshay on 20/03/2017.
 */

public class MainAdapter extends SectionedRecyclerViewAdapter<MainAdapter.MainVH> {

    @Override
    public int getSectionCount() {
        return 20;
    }

    @Override
    public int getItemCount(int section) {
        if (section % 2 == 0)
            return 4; // even sections get 4 items
        return 8; // odd get 8
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section) {
        holder.title.setText(String.format("Section %d", section));
    }

    @Override
    public void onBindViewHolder(MainVH holder, int section, int relativePosition, int absolutePosition) {
        holder.title.setText(String.format("S:%d, P:%d, A:%d", section, relativePosition, absolutePosition));
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        if (section == 1)
            return 0; // VIEW_TYPE_HEADER is -2, VIEW_TYPE_ITEM is -1. You can return 0 or greater.
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                layout = R.layout.list_item_header;
                break;
            case VIEW_TYPE_ITEM:
                layout = R.layout.list_item_main;
                break;
            default:
                layout = R.layout.list_item_main_bold;
                break;
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new MainVH(v);
    }

    public static class MainVH extends RecyclerView.ViewHolder {

        final TextView title;

        public MainVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}