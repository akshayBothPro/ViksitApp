package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.pojo.Opening;
import pro.viksit.com.viksit.role.adapter.HorSectionFooterViewHolder;
import pro.viksit.com.viksit.role.adapter.HorSectionHeaderViewHolder;
import pro.viksit.com.viksit.role.adapter.HorSectionItemViewHolder;
import pro.viksit.com.viksit.role.pojo.Role;

/*Created by Akshay on 30-03-2017.*/



public class HorizontalSectionedRecyclerViewAdapter extends SectionedRecyclerViewAdapter<HorSectionHeaderViewHolder,
        HorSectionItemViewHolder,
        HorSectionFooterViewHolder> {


    protected Context context = null;
    ArrayList<Role> recommendedList, finanaceList, salesList, roles;


    public HorizontalSectionedRecyclerViewAdapter(Context context,ArrayList<Role> recommendedList, ArrayList<Role> finanaceList,ArrayList<Role> salesList,ArrayList<Role> roles) {
        this.context = context;
        this.recommendedList = recommendedList;
        this.finanaceList = finanaceList;
        this.salesList = salesList;
        this.roles = roles;
    }

    @Override
    protected int getItemCountForSection(int section) {

        switch (section){
            case 0:
                return recommendedList.size();
            case 1:
                return finanaceList.size();
            case 2:
                return salesList.size();
            default:
                return  0;
        }

    }

    @Override
    protected int getSectionCount() {
        return 3;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return true;
    }

    protected LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(context);
    }

    @Override
    protected HorSectionHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_count_header, parent, false);
        return new HorSectionHeaderViewHolder(view);
    }

    @Override
    protected HorSectionFooterViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_count_footer, parent, false);
        return new HorSectionFooterViewHolder(view);
    }

    @Override
    protected HorSectionItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.role_horizontal_item_card_view, parent, false);
        return new HorSectionItemViewHolder(view);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(HorSectionHeaderViewHolder holder, int section) {
        if(section==0){
            holder.render("Recommended");
        }else if(section == 1){
            holder.render("Finance");
        }else if(section == 2){
            holder.render("Sales and Marketing");
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(HorSectionFooterViewHolder holder, int section) {
        holder.render("");
    }

    protected int[] colors = new int[]{0xfff44336, 0xff2196f3, 0xff009688, 0xff8bc34a, 0xffff9800};
    @Override
    protected void onBindItemViewHolder(HorSectionItemViewHolder holder, int section, int position) {
        if(section==0) {
            holder.render(recommendedList.get(position).getTitle(),recommendedList.get(position).getImageResID());
        } else if (section == 1) {
            holder.render(finanaceList.get(position).getTitle(), recommendedList.get(position).getImageResID());
        } else if (section == 2) {
            holder.render(salesList.get(position).getTitle(), recommendedList.get(position).getImageResID());
        }
    }

}
