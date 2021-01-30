package data.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataParser {

    private final static String pattern1 = "yyyy-MM-dd";
    private final static String pattern2 = "dd.MM.yyyy";


    public static String parseDateToStringFormatyyyMMdd(Date date){

       return new SimpleDateFormat(pattern1).format(date);

    }

    public static String parseDateToStringFormatddMMyyyy(Date date){
        return new SimpleDateFormat(pattern2).format(date);
    }

    public static Date parseStringToDateFormatyyyMMdd(String date){
        Date result = null;
        try {
            result = new SimpleDateFormat(pattern1).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseStringToDateFormatddMMyyyy(String date){
        Date result = null;
        try {
            result = new SimpleDateFormat(pattern2).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
