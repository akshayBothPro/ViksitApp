package pro.viksit.com.viksit.home.util;

import android.content.Context;

import java.util.concurrent.Callable;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.HttpUtil;

/**
 * Created by Feroz on 19-04-2017.
 */

public class DashBoardCallable implements Callable<String> {
    private Context context;
    private int user_id;
    public DashBoardCallable(Context context,int user_id){
        this.context = context;
        this.user_id = user_id;
    }

    @Override
    public String call() throws Exception {
        HttpUtil httpUtil = new HttpUtil();

        httpUtil.setUrl(context.getResources().getString(R.string.serverip) + (context.getResources().getString(R.string.dashboardcardurl).replaceAll("user_id", user_id + "")));
        httpUtil.setType("GET");

        return httpUtil.getStringResponse();
    }
}
