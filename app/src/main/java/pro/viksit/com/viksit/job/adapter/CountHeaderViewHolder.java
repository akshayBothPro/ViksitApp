package pro.viksit.com.viksit.job.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 25-03-2017.
 */

public class CountHeaderViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public CountHeaderViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_section_header);

    }
    public void render(String text){
        textView.setText(text);
    }
}
