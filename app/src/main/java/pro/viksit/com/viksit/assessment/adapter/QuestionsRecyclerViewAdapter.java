package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.pojo.Question;

/**
 * Created by Akshay on 22/03/2017.
 */

public class QuestionsRecyclerViewAdapter extends  RecyclerView.Adapter<QuestionsRecyclerViewAdapter.MyJobViewHolder>{

    private ArrayList<Question> questions = new ArrayList<>();
    private Context context;

    public QuestionsRecyclerViewAdapter(){

    }

    public QuestionsRecyclerViewAdapter(Context context, ArrayList<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public MyJobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return new MyJobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyJobViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionText.setText(question.getText());
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

        public MyJobViewHolder(View view) {
            super(view);
            questionText = (TextView) view.findViewById(R.id.tv_question_text);

        }

        @Override
        public void onClick(View view) {
            System.out.println(view);
        }

    }

}
