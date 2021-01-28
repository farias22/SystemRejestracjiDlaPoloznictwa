package models.comparators;

import models.PatientExtended;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class HospitalizationDateComparator {

    public static boolean before(String date1, String date2) {

        boolean result = false;

        Date d1 = null;
        Date d2 = null;

        String pattern = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            d1 = sdf.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            d2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (d2.before(d1)){
            result = true;
        }
        return result;
    }


    public static boolean after(String date1, String date2) {

        boolean result = false;

        Date d1 = null;
        Date d2 = null;

        String pattern = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            d1 = sdf.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            d2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (d2.after(d1)){
            result = true;
        }
        return result;
    }

}
