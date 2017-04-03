package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.Payment;

/**
 * Created by Akshay on 03/04/2017.
 */


public class CheckoutVerticalRecyclerAdapter extends RecyclerView.Adapter<CheckoutVerticalRecyclerAdapter.MyViewHolder>  {
    private ArrayList<Payment> paymentArrayList;
    private Context context;

    public CheckoutVerticalRecyclerAdapter(ArrayList<Payment> paymentArrayList,Context context) {
        this.paymentArrayList = paymentArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_payment_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Payment payment = paymentArrayList.get(position);
        holder.title.setText(payment.getName());
        holder.radioButton.setActivated(payment.getCheck());

    }

    @Override
    public int getItemCount() {
        return paymentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public RadioButton radioButton;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv_payment_title);
            radioButton = (RadioButton) view.findViewById(R.id.rbtn_payment);
        }

        @Override
        public void onClick(View view) {

        }

    }

}
