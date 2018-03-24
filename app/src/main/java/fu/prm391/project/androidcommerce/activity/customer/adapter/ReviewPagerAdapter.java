package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;

/**
 * Created by Khổng Cảnh on 3/24/2018.
 */

public class ReviewPagerAdapter extends PagerAdapter {
    private List<OrderItem> orderItems;
    private Context context;
    private LayoutInflater inflater;
    public ReviewPagerAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.review_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(orderItems.get(position).getProductId());

        container.addView(itemView);

        return itemView;
    }
}
