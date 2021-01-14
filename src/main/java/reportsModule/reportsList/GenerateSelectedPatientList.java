package reportsModule.reportsList;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import models.Patient;

import jxl.write.*;
import models.PatientExtended;
import models.comparators.PatientComparator;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class GenerateSelectedPatientList {


    public void generate(List<PatientExtended> patientList) throws WriteException, IOException {


        String path = "/home/pawel/Dokumenty/test/test.xls";


        File exlFile = new File(path);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(exlFile);

        WritableSheet writableSheet = wstawDane(patientList, writableWorkbook);
        writableWorkbook.write();
        writableWorkbook.close();
        Desktop.getDesktop().open(new File(path));
//        Files.deleteIfExists(Paths.get(path));

    }

    private WritableSheet wstawDane(List<PatientExtended> patientList, WritableWorkbook writableWorkbook) throws WriteException {
        WritableSheet s = writableWorkbook.createSheet("Lista pacjentek", 0);


        WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableCellFormat cf = new WritableCellFormat(wf);
        WritableFont wfBold = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD);
        WritableCellFormat cfBold = new WritableCellFormat(wfBold);
        WritableCellFormat cfNoWrap = new WritableCellFormat(wf);
        cf.setWrap(true);
        cfNoWrap.setWrap(false);


        List<String> naglowki = new ArrayList<>();
        naglowki.add("Lp");
        naglowki.add("Data wpisania do systemu");
        naglowki.add("Data hospitalizacji");
        naglowki.add("Wiek ciąży w dniu przyjęcia wg OM");
        naglowki.add("Imię");
        naglowki.add("Nazwisko");
        naglowki.add("PESEL");
        naglowki.add("Czy planowane przyjęcie");
        naglowki.add("Rozpoznanie");
        naglowki.add("Data ostatniej miesiączki");
        naglowki.add("Lekarz kierujący");
        naglowki.add("Lekarz zapisujący");
        naglowki.add("Komentarz");


        int wiersz = 1;
        int kolumna = 0;

        for (String s1 : naglowki) {
            Label label1 = new Label(kolumna, 0, s1, cfBold);
            s.addCell(label1);
            kolumna++;
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        Collections.sort(patientList, new PatientComparator());

        for (Patient patient : patientList) {
            Label label1 = new Label(0, wiersz, String.valueOf(wiersz), cfNoWrap);
            s.addCell(label1);

            Date registrationDate = patient.getRegistrationDate();
            String registrationDate2 = simpleDateFormat.format(registrationDate);
            Label label2 = new Label(1, wiersz, registrationDate2, cfNoWrap);
            s.addCell(label2);

            Date hospitalizationDateDate = patient.getHospitalizationDate();
            String hospitalizationDateDate2 = simpleDateFormat.format(hospitalizationDateDate);
            Label label3 = new Label(2, wiersz, hospitalizationDateDate2.equals("1900-01-01") ? "*":hospitalizationDateDate2, cfNoWrap);
            s.addCell(label3);

            Label label4 = new Label(3, wiersz, String.valueOf(patient.getPregnancyAge()), cfNoWrap);
            s.addCell(label4);

            Label label5 = new Label(4, wiersz, patient.getFirstName(), cfNoWrap);
            s.addCell(label5);

            Label label6 = new Label(5, wiersz, patient.getLastName(), cfNoWrap);
            s.addCell(label6);

            Label label7 = new Label(6, wiersz, patient.getPesel(), cfNoWrap);
            s.addCell(label7);

            Label label8 = new Label(7, wiersz, patient.isScheludedRegistration() ? "TAK" : "NIE", cfNoWrap);
            s.addCell(label8);

            Label label9 = new Label(8, wiersz, patient.getDiagnosis(), cfNoWrap);
            s.addCell(label9);

            Date lastPeriodDate = patient.getLastPeriodDate();
            String lastPeriodDate2 = simpleDateFormat.format(lastPeriodDate);
            Label label10 = new Label(9, wiersz, lastPeriodDate2, cfNoWrap);
            s.addCell(label10);

            Label label11 = new Label(10, wiersz, patient.getRefferingDoctor(), cfNoWrap);
            s.addCell(label11);

            Label label12 = new Label(11, wiersz, patient.getPrescribingDoctor(), cfNoWrap);
            s.addCell(label12);

            Label label13 = new Label(12, wiersz, patient.getComment(), cfNoWrap);
            s.addCell(label13);
            wiersz++;
        }


        for (int x = 0; x < 13; x++) {
            CellView cellV = s.getColumnView(x);
            cellV.setAutosize(true);
            s.setColumnView(x, cellV);
        }
        return s;
    }
}


