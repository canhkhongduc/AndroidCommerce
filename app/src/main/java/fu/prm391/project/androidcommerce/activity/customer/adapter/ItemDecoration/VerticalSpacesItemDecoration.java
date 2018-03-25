package fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lam on 3/25/2018.
 */

public class VerticalSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public VerticalSpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = space;
    }
}
