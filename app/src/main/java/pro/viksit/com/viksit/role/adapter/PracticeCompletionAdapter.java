package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.Practice;

/**
 * Created by Akshay on 17/04/2017.
 */


public class PracticeCompletionAdapter extends RecyclerView.Adapter<PracticeCompletionAdapter.MyViewHolder>  {
    private ArrayList<Practice> list;
    private Context context;

    public PracticeCompletionAdapter(ArrayList<Practice> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pratice_completion_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Practice item = list.get(position);
        holder.title.setText(item.getText());
        holder.progress.setMax(item.getMax());
        holder.progress.setProgress(item.getProgress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public ProgressBar progress;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv_practice_item_text);
            progress = (ProgressBar) view.findViewById(R.id.pb_practice_item_progress);
        }

        @Override
        public void onClick(View view) {

        }

    }

}
