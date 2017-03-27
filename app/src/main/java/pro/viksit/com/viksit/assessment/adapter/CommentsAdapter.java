package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.pojo.Comment;

/**
 * Created by Akshay on 27/03/2017.
 */

public class CommentsAdapter extends  RecyclerView.Adapter<CommentsAdapter.MyJobViewHolder>{

    private ArrayList<Comment> comments = new ArrayList<>();
    private Context context;

    public CommentsAdapter(){
    }

    public CommentsAdapter(Context context, ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public MyJobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);

        return new MyJobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyJobViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.image.setImageResource(comment.getImageResID());
        holder.commentorName.setText(comment.getCommentor());
        holder.time.setText(comment.getTime());
        holder.text.setText(comment.getCommentText());
        if(position % 2 == 0){
            holder.vLine.setVisibility(View.GONE);
        }else {
            holder.vLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (comments == null)
            return 0;
        else
            return  comments.size();
    }

    public class MyJobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CircularImageView image;
        public TextView commentorName;
        public TextView time;
        public TextView text;
        public View vLine;


        public MyJobViewHolder(View view) {
            super(view);
            image = (CircularImageView) view.findViewById(R.id.iv_commentor_circle_image);
            commentorName = (TextView) view.findViewById(R.id.tv_commentor_name);
            time = (TextView) view.findViewById(R.id.tv_time);
            text = (TextView) view.findViewById(R.id.tv_comment_text);
            vLine = view.findViewById(R.id.v_fade);

        }

        @Override
        public void onClick(View view) {
            System.out.println(view);
        }

    }

}
