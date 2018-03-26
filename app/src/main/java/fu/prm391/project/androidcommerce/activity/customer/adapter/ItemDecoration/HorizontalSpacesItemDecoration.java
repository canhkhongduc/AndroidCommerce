package fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lam on 3/25/2018.
 */

public class HorizontalSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean excludeOddNumberItems;

    public HorizontalSpacesItemDecoration(int space, boolean excludeOddNumberItems) {
        this.space = space;
        this.excludeOddNumberItems = excludeOddNumberItems;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (position != 0) {
            if (!excludeOddNumberItems || position % 2 == 1) {
                outRect.left = space;
            }
        }
    }
}
