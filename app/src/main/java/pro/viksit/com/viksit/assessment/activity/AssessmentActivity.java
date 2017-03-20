package pro.viksit.com.viksit.assessment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.LockableViewPager;
import pro.viksit.com.viksit.assessment.adapter.AssessmentAdapter;
import pro.viksit.com.viksit.assessment.pojo.Option;
import pro.viksit.com.viksit.assessment.pojo.Question;

public class AssessmentActivity extends AppCompatActivity {

    private ArrayList<Question> questionArrayList;
    private LockableViewPager lockableViewPager;
    private AssessmentAdapter assessmentAdapter;
    private static Button check_answer;
    public static Drawer result = null;
    private ArrayList<PrimaryDrawerItem> primaryDrawerItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        lockableViewPager = (LockableViewPager) findViewById(R.id.viewpager);
        check_answer = (Button) findViewById(R.id.check_answer);
        ImageButton imageButton = (ImageButton) findViewById(R.id.smiley);
        questionArrayList = new ArrayList<>();
        setupData();

        assessmentAdapter = new AssessmentAdapter(getSupportFragmentManager(), questionArrayList);
        lockableViewPager.setAdapter(assessmentAdapter);
        lockableViewPager.setSwipeLocked(true);

        primaryDrawerItems = new ArrayList<>();

        int counter=0;
        for(Question question3: questionArrayList){
            PrimaryDrawerItem jj =  new PrimaryDrawerItem().withName(question3.getText()).withIdentifier(counter);
            primaryDrawerItems.add(jj);
            counter++;

        }


        result = new DrawerBuilder()
                .withActivity(this).withDrawerWidthDp(280).withDrawerGravity(Gravity.RIGHT)
                .withStickyHeader(R.layout.sticky_header)
                .addDrawerItems(primaryDrawerItems.toArray(new PrimaryDrawerItem[primaryDrawerItems.size()]))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;

                            lockableViewPager.setCurrentItem((int) drawerItem.getIdentifier());
                        }
                        return false;

                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
        result.getStickyHeader().setBackgroundColor(getResources().getColor(R.color.theme_color));
        TextView vv = (TextView) result.getStickyHeader().findViewById(R.id.question_title);
        vv.setText("Champa ka ram ram");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.openDrawer();
            }
        });
    }

    public static void changeCheckbutton() {

        check_answer.setText("Submit");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
/*
        outState = headerResult.saveInstanceState(outState);
*/

        //add the values which need to be saved from the accountHeader to the bundle
        super.onSaveInstanceState(outState);
    }


    public void setupData(){
        Option option1 = new Option("A. 100 kmph");
        Option option2 = new Option("B. 110 kmph");
        Option option3 = new Option("C. 130 kmph");
        Option option4 = new Option("D. 140 kmph");
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(option1);
        optionArrayList.add(option2);
        optionArrayList.add(option3);
        optionArrayList.add(option4);
        Question question = new Question("A train can travel 50% faster than a car. Both start from point A at the same time and reach point B 75 kms away from A at the same time. On the way, however, the train lost about 12.5 minutes while stopping at the stations. The speed of the car is:", optionArrayList);




        Option option5 = new Option("A. The ski instructors at Top of the Peak Ski School work from December through March.");
        Option option6 = new Option("B. Matthew prefers jobs that allow him to work outdoors.");
        Option option7 = new Option("C. Lucinda makes an appointment with the beach resort restaurant manager to interview for the summer waitressing position that was advertised in the newspaper.");
        Option option8 = new Option("D. Doug's ice cream shop stays open until 11 p.m. during the summer months.");
        ArrayList<Option> optionArrayList1 = new ArrayList<>();
        optionArrayList1.add(option5);
        optionArrayList1.add(option6);
        optionArrayList1.add(option7);
        optionArrayList1.add(option8);
        Question question1 = new Question("\t\n" +
                "Applying for Seasonal Employment occurs when a person requests to be considered for a job that is dependent on a particular season or time of year. Which situation below is the best example of Applying for Seasonal Employment?", optionArrayList1);




        questionArrayList.add(question);
        questionArrayList.add(question1);


    }
}
