package pro.viksit.com.viksit.role.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 22-03-2017.
 */

public class SessionCards extends Fragment {
    private CardView mCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.session_cards, container, false);
        mCardView = (CardView) view.findViewById(R.id.card_view);

        return view;
    }
    public CardView getCardView() {
        return mCardView;
    }
}
