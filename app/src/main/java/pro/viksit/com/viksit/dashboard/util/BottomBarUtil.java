package pro.viksit.com.viksit.dashboard.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.calendar.activity.CalendarActivity;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.job.activity.JobActivity;
import pro.viksit.com.viksit.role.activity.RoleActivity;

/**
 * Created by Feroz on 17-03-2017.
 */

public class BottomBarUtil {

    public void setupBottomBar(BottomNavigationView bottomNavigationView,final Context context,int i){
        removeShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(i);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.role:
                                if(((Activity)context) instanceof  RoleActivity){
                                    System.out.println("Dont call role in role ... ... .... ");
                                }else {
                                    Intent i = new Intent(context, RoleActivity.class);
                                    context.startActivity(i);
                                    ((Activity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                                }
                                    break;
                            case R.id.task:
                                if(((Activity)context) instanceof  DashboardActivity) {
                                    System.out.println("Dont call dashboard in dashboard ... ... .... ");

                                }else {
                                    Intent ii = new Intent(context, DashboardActivity.class);
                                    context.startActivity(ii);
                                    ((Activity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                                }
                                break;
                            case R.id.challenge:
                                break;
                            case R.id.job:
                                if(((Activity)context) instanceof JobActivity) {
                                    System.out.println("Dont call Job in Job ... ... .... ");

                                }else {
                                    Intent ii = new Intent(context, JobActivity.class);
                                    context.startActivity(ii);
                                    ((Activity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                                }
                                break;
                            case R.id.calendar:
                                if(((Activity)context) instanceof CalendarActivity) {
                                    System.out.println("Dont call Job in Job ... ... .... ");

                                }else {
                                    Intent ii = new Intent(context, CalendarActivity.class);
                                    context.startActivity(ii);
                                    ((Activity) context).overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                                }
                                break;
                        }
                        return true;
                    }
                });
    }

    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }


}
