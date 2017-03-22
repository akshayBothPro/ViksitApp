package pro.viksit.com.viksit.calendar.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.calendar.adapter.TimeLineAdapter;
import pro.viksit.com.viksit.calendar.pojo.CalendarData;

public class CalendarActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<CalendarData> calendarDataList = new ArrayList<>();
    private  TimeLineAdapter timeLineAdapter;
    private ImageButton imageButton;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imageButton = (ImageButton) findViewById(R.id.calendar);

        mRecyclerView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        initView();




        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("January", "February", "March", "April", "May","June","July","August","September","October","November","December");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item+" position "+position, Snackbar.LENGTH_LONG).show();
                setRecyclerMonthlydata(item,position);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth+"/"+monthOfYear+"/"+year;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }




    private void initView() {
        Calendar addday = Calendar.getInstance();
        addday.setTime(new Date());
        addday.add(Calendar.DATE, -1);
        Date previousday = addday.getTime();
        CalendarData cal2 = new CalendarData(previousday,"Business Management at 9 Pm","NOWHERE","");
        calendarDataList.add(cal2);

        CalendarData cal = new CalendarData(previousday,"Sales Class is at 3 Pm","INACTIVE","");
        calendarDataList.add(cal);

        Calendar cals = Calendar.getInstance();
        cals.setTime(new Date());
        cals.add(Calendar.HOUR, -1);
        Date oneHourBack = cals.getTime();

        CalendarData cal1 = new CalendarData(oneHourBack,"Sales Assessment at 6 Pm","ACTIVE","");
        calendarDataList.add(cal1);

        CalendarData cal12 = new CalendarData(new Date(),"Current Assessment at 6 Pm","ACTIVE","");
        calendarDataList.add(cal12);

        Calendar minusday = Calendar.getInstance();
        minusday.setTime(new Date());
        minusday.add(Calendar.DATE, 1);
        Date nextday = minusday.getTime();

        CalendarData cal4 = new CalendarData(new Date(),"Practise Session at 1 Pm","ACTIVE","");
        calendarDataList.add(cal4);
        CalendarData cal5 = new CalendarData(new Date(),"Enjoyment","INACTIVE","");
        calendarDataList.add(cal5);


        CalendarData cal3 = new CalendarData(nextday,"Business Sales at 11 Am","INACTIVE","");
        calendarDataList.add(cal3);
        CalendarData cal9 = new CalendarData(nextday,"Business Sales at 11 Am","INACTIVE","");
        calendarDataList.add(cal9);
        timeLineAdapter = new TimeLineAdapter(calendarDataList,CalendarActivity.this);
        mRecyclerView.setAdapter(timeLineAdapter);
    }

    private void setRecyclerMonthlydata(String item, int position) {
         List<CalendarData> monthlydata = new ArrayList<>();
        for(CalendarData calendarData:calendarDataList){
            calendarData.setEvent_name(item+" for "+calendarData.getEvent_name());
            monthlydata.add(calendarData);
        }

        timeLineAdapter = new TimeLineAdapter(monthlydata,CalendarActivity.this);
        mRecyclerView.setAdapter(timeLineAdapter);
        timeLineAdapter.notifyDataSetChanged();

    }


}
