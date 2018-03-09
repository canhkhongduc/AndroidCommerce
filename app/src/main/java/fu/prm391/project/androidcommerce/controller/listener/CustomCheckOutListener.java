package fu.prm391.project.androidcommerce.controller.listener;

import android.view.View;

/**
 * Created by Khổng Cảnh on 3/4/2018.
 */

public interface CustomCheckOutListener {
    public abstract void onItemRemove(View view, int position);
    public abstract void onItemEdit(View view, int position);
}
