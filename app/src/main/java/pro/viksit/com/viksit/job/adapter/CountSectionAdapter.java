package pro.viksit.com.viksit.job.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.pojo.Opening;


/**
 * Created by Feroz on 25-03-2017.
 */

public class CountSectionAdapter extends SectionedRecyclerViewAdapter<CountHeaderViewHolder,
        CountItemViewHolder,
        CountFooterViewHolder> {


    protected Context context = null;
    ArrayList<Opening> jobinvitelist,jobofferllist,jobdata;


    public CountSectionAdapter(Context context,ArrayList<Opening> jobinvitelist,ArrayList<Opening> jobofferllist,ArrayList<Opening> jobdata) {
        this.context = context;
        this.jobinvitelist = jobinvitelist;
        this.jobofferllist = jobofferllist;
        this.jobdata = jobdata;
    }

    @Override
    protected int getItemCountForSection(int section) {

        switch (section){
            case 0:
                return jobinvitelist.size();
            case 1:
                return jobofferllist.size();
            default:
                return  0;
        }

    }

    @Override
    protected int getSectionCount() {
        return 2;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return true;
    }

    protected LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(context);
    }

    @Override
    protected CountHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_count_header, parent, false);
        return new CountHeaderViewHolder(view);
    }

    @Override
    protected CountFooterViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_count_footer, parent, false);
        return new CountFooterViewHolder(view);
    }

    @Override
    protected CountItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.opening_job_card_item, parent, false);
        return new CountItemViewHolder(view);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(CountHeaderViewHolder holder, int section) {
        if(section==0){
            holder.render("Invites");
        }else{
            holder.render("Open Jobs");
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(CountFooterViewHolder holder, int section) {
        holder.render("");
    }

    protected int[] colors = new int[]{0xfff44336, 0xff2196f3, 0xff009688, 0xff8bc34a, 0xffff9800};
    @Override
    protected void onBindItemViewHolder(CountItemViewHolder holder, int section, int position) {
            if(section==0) {
                holder.render(jobinvitelist.get(position).getJobProfile(),jobinvitelist.get(position).getCompanyName(),jobinvitelist.get(position).getExpiryDate(),jobinvitelist.get(position).getImageResID());
            } else if (section == 1) {
                holder.render(jobofferllist.get(position).getJobProfile(), jobofferllist.get(position).getCompanyName(), jobofferllist.get(position).getExpiryDate(), jobofferllist.get(position).getImageResID());
            }
    }

}
