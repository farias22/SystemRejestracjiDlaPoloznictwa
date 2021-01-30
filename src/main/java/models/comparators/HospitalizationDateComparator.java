package models.comparators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static data.converter.DataParser.parseStringToDateFormatddMMyyyy;

public class HospitalizationDateComparator {

    public static boolean before(String date1, String date2) {

        boolean result = false;

        Date d1 = parseStringToDateFormatddMMyyyy(date1);
        Date d2 = parseStringToDateFormatddMMyyyy(date2);


        if (d2.before(d1)){
            result = true;
        }
        return result;
    }


    public static boolean after(String date1, String date2) {

        boolean result = false;

        Date d1 = parseStringToDateFormatddMMyyyy(date1);
        Date d2 = parseStringToDateFormatddMMyyyy(date2);


        if (d2.after(d1)){
            result = true;
        }
        return result;
    }

}
