package reportsModule.reportsList;


import models.Patient;
import models.PatientExtended;
import models.comparators.PatientComparator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GenerateSelectedPatientList {


    public Workbook generate(List<PatientExtended> patientList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        generateFileData(patientList, workbook);
        return workbook;
    }


    public Sheet generateFileData(List<PatientExtended> patientList, Workbook workbook) {

        Sheet s = workbook.createSheet("Lista pacjentek");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.OLIVE_GREEN.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);


        Row headerRow = s.createRow(0);


        List<String> columns = new ArrayList<>();
        columns.add("Lp");
        columns.add("Data wpisania do systemu");
        columns.add("Data hospitalizacji");
        columns.add("Wiek ciąży w dniu przyjęcia wg OM");
        columns.add("Imię");
        columns.add("Nazwisko");
        columns.add("PESEL");
        columns.add("Czy planowane przyjęcie");
        columns.add("Rozpoznanie");
        columns.add("Data ostatniej miesiączki");
        columns.add("Lekarz kierujący");
        columns.add("Lekarz zapisujący");
        columns.add("Komentarz");


        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
            cell.setCellStyle(headerCellStyle);
        }

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        Collections.sort(patientList, new PatientComparator());

        int rowNum = 1;
        int rowNum2 = 1;

        for (Patient patient : patientList) {
            Row row = s.createRow(rowNum++);

            row.createCell(0).setCellValue(String.valueOf(rowNum2));

            Date registrationDate = patient.getRegistrationDate();
            String registrationDate2 = simpleDateFormat.format(registrationDate);
            row.createCell(1).setCellValue(registrationDate2);

            Date hospitalizationDateDate = patient.getHospitalizationDate();
            String hospitalizationDateDate2 = simpleDateFormat.format(hospitalizationDateDate);
            row.createCell(2).setCellValue(hospitalizationDateDate2.equals("1900-01-01") ? "*" : hospitalizationDateDate2);

            row.createCell(3).setCellValue(String.valueOf(patient.getPregnancyAge()));

            row.createCell(4).setCellValue(patient.getFirstName());

            row.createCell(5).setCellValue(patient.getLastName());

            row.createCell(6).setCellValue(patient.getPesel());

            row.createCell(7).setCellValue(patient.isScheludedRegistration() ? "TAK" : "NIE");

            row.createCell(8).setCellValue(patient.getDiagnosis());

            Date lastPeriodDate = patient.getLastPeriodDate();
            String lastPeriodDate2 = simpleDateFormat.format(lastPeriodDate);
            row.createCell(9).setCellValue(lastPeriodDate2);

            row.createCell(10).setCellValue(patient.getRefferingDoctor());

            row.createCell(11).setCellValue(patient.getPrescribingDoctor());

            row.createCell(12).setCellValue(patient.getComment());

            rowNum2++;
        }


        for (int i = 0; i < columns.size(); i++) {
            s.autoSizeColumn(i);
        }
        return s;
    }

}


