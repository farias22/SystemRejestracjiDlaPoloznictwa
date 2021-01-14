package reports.reportsList;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import models.Patient;

import jxl.write.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GenerateSelectedPatientList {


    public void generate(List<Patient> patientList) throws WriteException, IOException {


        String path = "/home/pawel/Dokumenty/test/test.xls";


        File exlFile = new File(path);
        WritableWorkbook writableWorkbook = Workbook.createWorkbook(exlFile);

        WritableSheet writableSheet = wstawDane(patientList, writableWorkbook);
        writableWorkbook.write();
        writableWorkbook.close();
        Desktop.getDesktop().open(new File(path));
//        Files.deleteIfExists(Paths.get(path));

    }

    private WritableSheet wstawDane(List<Patient> patientList, WritableWorkbook writableWorkbook) throws WriteException {
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

        for (Patient patient : patientList) {
            Label label1 = new Label(0, wiersz, String.valueOf(wiersz), cfNoWrap);
            s.addCell(label1);
            Label label2 = new Label(1, wiersz, patient.getRegistrationDate().toString(), cfNoWrap);
            s.addCell(label2);
            Label label3 = new Label(2, wiersz, patient.getHospitalizationDate().toString(), cfNoWrap);
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

            Label label10 = new Label(9, wiersz, patient.getLastPeriodDate().toString(), cfNoWrap);
            s.addCell(label10);

            Label label11 = new Label(9, wiersz, patient.getRefferingDoctor(), cfNoWrap);
            s.addCell(label11);

            Label label12 = new Label(9, wiersz, patient.getPrescribingDoctor(), cfNoWrap);
            s.addCell(label12);

            Label label13 = new Label(9, wiersz, patient.getComment(), cfNoWrap);
            s.addCell(label13);
            wiersz++;
        }


        for (int x = 0; x < 10; x++) {
            CellView cellV = s.getColumnView(x);
            cellV.setAutosize(true);
            s.setColumnView(x, cellV);
        }
        return s;
    }
}


