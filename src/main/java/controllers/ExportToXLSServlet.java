package controllers;

import dao.impl.MySQLPatientDao;
import error.ValidationError;
import jxl.write.WriteException;
import models.PatientExtended;
import org.apache.poi.ss.usermodel.Workbook;
import reportsModule.PatientsReportsGenerator;
import services.PatientListAppService;
import services.impl.PatientListAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ExportToXLSServlet", value = "/exportList")
public class ExportToXLSServlet extends HttpServlet {


    private PatientListAppService patientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao(), new PatientsReportsGenerator());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<PatientExtended> list = (List<PatientExtended>) req.getSession().getAttribute(ServletUtils.PATIENT_LIST);
        List<PatientExtended> listToExcell = new ArrayList<>();
        for (PatientExtended patientExtended : list) {
            if (patientExtended.isBasket()) {
                listToExcell.add(patientExtended);
            }
        }

        if (listToExcell.size() == 0) {
            ArrayList<ValidationError> errors = new ArrayList<>();
            ValidationError emptyList = new ValidationError(ServletUtils.EMPTY_XLS_LIST_HEADER, ServletUtils.EMPTY_XLS_LIST_MESSAGE);
            errors.add(emptyList);
            req.setAttribute(ServletUtils.EMPTY_XLS_LIST_ERROR, errors);
            req.getRequestDispatcher("/patientList.jsp").forward(req, resp);
            return;
        }
        Workbook workbook;
        try {
            workbook = patientService.exportListToXLS(listToExcell);
            resp.setHeader("Content-Disposition", "inline; filename*=UTF-8''ListaPacjentek.xlsx");
            resp.setHeader("Content-Type", "application/octet-stream");
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (WriteException e) {
            e.printStackTrace();
        }
//        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}
