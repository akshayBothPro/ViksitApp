package pro.viksit.com.viksit.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.challenge.pojo.StudentRankPOJO;
import pro.viksit.com.viksit.util.CircleTransform;
import pro.viksit.com.viksit.challenge.pojo.LeaderBoardCourse;
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.util.SaveImageAsync;

/**
 * Created by Akshay on 13/04/2017.
 */


public class LeaderBoardRecyclerAdapter extends RecyclerView.Adapter<LeaderBoardRecyclerAdapter.MyViewHolder>  {
    private ArrayList<StudentRankPOJO> list;
    private Context context;
    private int screenWidth,screenHeight;
    private double diagonalInches;
    private Picasso picasso;

    public LeaderBoardRecyclerAdapter(ArrayList<StudentRankPOJO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public LeaderBoardRecyclerAdapter(ArrayList<StudentRankPOJO> list , Context context, int screenWidth, int screenHeight, double diagonalInches) {
        this.list = list;
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.diagonalInches = diagonalInches;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_list_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        StudentRankPOJO profile = list.get(position);
       // if(position != 0 || position != 1 || position != 2) {
            holder.name.setText(profile.getName().toLowerCase());
            holder.rank.setText(ordinal(profile.getBatchRank()));
            holder.xp.setText(Integer.toString(profile.getPoints()));

           String url =  profile.getImageURL();
            if(!url.startsWith("http")){
                url+=context.getResources().getString(R.string.resourceserverip)+url;
            }

            int index = url.lastIndexOf("/");

            ImageSaver studentImage = new ImageSaver(context).
                    setParentDirectoryName("leaderboard").
                    setFileName(new DisplayUtil().getFileNameReplaced(url.substring(index+1,url.length()))).
                    setExternal(ImageSaver.isExternalStorageReadable());

            picasso = Picasso.with(context);
            if(studentImage.checkFile()){
                picasso.load(studentImage.pathFile()).transform(new CircleTransform()).into(holder.image);
            }else{
                picasso.load(url).transform(new CircleTransform()).into(holder.image);//image
                new SaveImageAsync(studentImage).execute(url);
            }

            //for tablets
            if (diagonalInches >= 6.5) {
                ViewGroup.LayoutParams params = holder.image.getLayoutParams();
                params.height = screenHeight / 11;
                params.width = screenHeight / 11;
                holder.image.setLayoutParams(params);
            }
        //}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  String ordinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView name, rank, xp;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.iv_item_image);
            name = (TextView) view.findViewById(R.id.tv_item_name);
            rank = (TextView) view.findViewById(R.id.tv_item_rank);
            xp = (TextView) view.findViewById(R.id.tv_item_xp);

        }

        @Override
        public void onClick(View view) {

        }

    }

}