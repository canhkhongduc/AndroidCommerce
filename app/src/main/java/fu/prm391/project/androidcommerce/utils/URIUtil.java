package fu.prm391.project.androidcommerce.utils;

import android.net.Uri;

import fu.prm391.project.androidcommerce.R;

/**
 * Created by Khổng Cảnh on 3/18/2018.
 */

public class URIUtil {
    public String getURLForResource (int resourceId) {
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}
