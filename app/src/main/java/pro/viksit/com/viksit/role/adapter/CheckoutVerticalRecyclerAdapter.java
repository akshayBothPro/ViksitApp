package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.Payment;

/**
 * Created by Akshay on 03/04/2017.
 */

public class CheckoutVerticalRecyclerAdapter extends RecyclerView.Adapter<CheckoutVerticalRecyclerAdapter.MyViewHolder>  {
    private List<Payment> paymentList;
    private Context context;

    public CheckoutVerticalRecyclerAdapter(List<Payment> paymentList,Context context) {
        this.paymentList = paymentList;
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
        Payment payment = paymentList.get(position);
        holder.title.setText(payment.getName());
        /*holder.check;*/
        /*Picasso.with(context).load(role.getImageURL()).transform(new CircleTransform()).into(holder.image);*/
        // if we have image URL instead of resID use it
        /*URL url = role.getImageURL();
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        holder.image.setImageBitmap(bmp);*/
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public RadioButton check;
        public TextView title;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_role_vertical_item_title);
            check = (RadioButton) view.findViewById(R.id.rbtn_payment);
        }

        @Override
        public void onClick(View view) {

        }

    }

}
