package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.LockableViewPager;
import pro.viksit.com.viksit.assessment.activity.AssessmentActivity;
import pro.viksit.com.viksit.assessment.pojo.Question;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;

/**
 * Created by Akshay on 22/03/2017.
 */

public class QuestionsRecyclerViewAdapter extends  RecyclerView.Adapter<QuestionsRecyclerViewAdapter.MyJobViewHolder>{

    private List<QuestionPOJO> questions;
    private Context context;
    private LockableViewPager lockableViewPager;



    public QuestionsRecyclerViewAdapter(Context context,LockableViewPager lockableViewPager,List<QuestionPOJO> questions) {
        this.context = context;
        this.questions = questions;
        this.lockableViewPager =lockableViewPager;
    }

    @Override
    public MyJobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return new MyJobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyJobViewHolder holder, int position) {
        QuestionPOJO question = questions.get(position);
        holder.questionText.setText((position+1)+". "+Html.fromHtml(question.getText()));
    }

    @Override
    public int getItemCount() {
        if (questions == null)
            return 0;
        else
            return  questions.size();
    }

    public class MyJobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView questionText;
        public RelativeLayout main_layout;
        public MyJobViewHolder(View view) {
            super(view);
            questionText = (TextView) view.findViewById(R.id.tv_question_text);
            main_layout = (RelativeLayout) view.findViewById(R.id.main_layout);
            main_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("Call ho gaya");
            lockableViewPager.setCurrentItem(getAdapterPosition());
        }

    }

}
