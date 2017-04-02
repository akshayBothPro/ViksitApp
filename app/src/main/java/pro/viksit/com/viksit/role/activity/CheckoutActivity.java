package pro.viksit.com.viksit.role.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.R;
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
    private List<Payment> paymentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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
}
