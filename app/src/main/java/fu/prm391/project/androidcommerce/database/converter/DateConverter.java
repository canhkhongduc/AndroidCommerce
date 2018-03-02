package fu.prm391.project.androidcommerce.database.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Lam on 2/28/2018.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
