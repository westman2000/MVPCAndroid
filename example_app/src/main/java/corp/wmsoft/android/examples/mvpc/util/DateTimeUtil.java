package corp.wmsoft.android.examples.mvpc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by admin on 8/4/16.
 *
 */
public class DateTimeUtil {


    public static String formatMillisecondsToString(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return format.format(calendar.getTime());
    }
}
