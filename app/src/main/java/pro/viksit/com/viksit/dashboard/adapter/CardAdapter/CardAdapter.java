package pro.viksit.com.viksit.dashboard.adapter.CardAdapter;

/**
 * Created by Feroz on 18-03-2017.
 */
import android.support.v7.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}