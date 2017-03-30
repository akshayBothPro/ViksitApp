package pro.viksit.com.viksit.role.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pro.viksit.com.viksit.R;

/**
 * Created by Akshay on 30/03/2017.
 */


public class HorSectionHeaderViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public HorSectionHeaderViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_section_header);

    }
    public void render(String text){
        textView.setText(text);
    }
}
