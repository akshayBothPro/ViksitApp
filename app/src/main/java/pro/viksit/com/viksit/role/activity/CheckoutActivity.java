package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.role.adapter.CheckoutVerticalRecyclerAdapter;
import pro.viksit.com.viksit.role.pojo.Payment;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class CheckoutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView backBtn;
    private TextView barTitle;
    private ImageView image;
    private TextView title;
    private TextView amount;
    private RecyclerView verticalRecycler;
    private CheckoutVerticalRecyclerAdapter adapter;
    private ArrayList<Payment> paymentArrayList;

    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getWidthAndHeight();
        Double d, d1;
        if (diagonalInches>=6.5){
            // 6.5inch device or bigger
            d = new Double(screenWidth / 1.2);
            d1= new Double(screenHeight/1.3);
        }else{
            // smaller device
            d = new Double(screenWidth / 1.2);
            d1= new Double(screenHeight/1.6);
        }
        int screenwidth = d.intValue();;
        int screenheitght = d1.intValue();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        backBtn = (ImageView) findViewById(R.id.iv_back_btn);
        barTitle = (TextView) findViewById(R.id.checkout_title);
        image =(ImageView) findViewById(R.id.iv_checkout_image);
        title = (TextView) findViewById(R.id.tv_checkout_role_title);
        amount = (TextView) findViewById(R.id.tv_amount_digits);
        verticalRecycler = (RecyclerView) findViewById(R.id.rv_payment_option);

        setSupportActionBar(toolbar);
        setPaymentOptionsData();

        implementActions();


    }

    private void implementActions(){
        Picasso.with(getBaseContext()).load(R.drawable.ic_1).transform(new CircleTransform()).into(image);
        if(screenHeight != 0 && screenWidth != 0) {
            ViewGroup.LayoutParams params = image.getLayoutParams();
            params.height = screenHeight/6;
            params.width = screenHeight/6;
            image.setLayoutParams(params);
        }

        adapter = new CheckoutVerticalRecyclerAdapter(paymentArrayList,getBaseContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(adapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(CheckoutActivity.this, ModuleActivity.class);
                       /* Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);*/
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );

    }

    private void setPaymentOptionsData(){
        paymentArrayList = new ArrayList<>();
        Payment payment;

        payment = new Payment(false,"Debit/Credit Card");
        paymentArrayList.add(payment);

        payment = new Payment(false,"Netbanking");
        paymentArrayList.add(payment);

        payment = new Payment(false,"Paytm");
        paymentArrayList.add(payment);


    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        float yInches= displaymetrics.heightPixels/displaymetrics.ydpi;
        float xInches= displaymetrics.widthPixels/displaymetrics.xdpi;
        diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
    }
}
