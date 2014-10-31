package org.pflood.spring32.controller;

import org.pflood.data.*;
import org.pflood.model.HealthCheckRequestXLSXFileParser;
import org.pflood.model.HealthCheckResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class HealthCheckResultController {

    private ArrayList<ArrayList<String>> sheetData;

    HealthCheckRequestXLSXFileParser requestParser;

    @Autowired
    ServletContext servletContext;
    private SQLServerDBAO pf_data;

    @RequestMapping(value="health_check_result.do", method = RequestMethod.POST)
    public ModelAndView healthCheckResult(@RequestParam("program") String program,
                                          @RequestParam("file") MultipartFile uploadedFile,
                                          @RequestParam("fileformat") String fileFormat)
            throws UnavailableException {

        pf_data = (SQLServerDBAO) servletContext.getAttribute("pf_data");

        if (pf_data == null) {
            throw new UnavailableException("Couldn't get database.");
        }

        // Form validation
        // TODO - Replace with client side validation
        if (uploadedFile.isEmpty() == true && program.isEmpty() == true){
            return new ModelAndView("error_pages/error_page_both");
        }
        if (uploadedFile.isEmpty() == true){
            return new ModelAndView("error_pages/error_page_file");
        }
        if (program.isEmpty() == true){
            return new ModelAndView("error_pages/error_page_program");
        }

        if (fileFormat.equals("XLSX"))
            requestParser = new HealthCheckRequestXLSXFileParser(uploadedFile);
        if (fileFormat.equals("CSV"))
            return new ModelAndView("csv_unsupported");

        sheetData = requestParser.getSheetData();

        try
        {
            // Save file data to DB and get results
            pf_data.saveTest(sheetData);
            ArrayList<HealthCheckResult> testResults = pf_data.getTestResults();

            return new ModelAndView("health_check_results", "testresults", testResults);
        }
        catch (Exception ex)
        {
            Logger.getLogger(HealthCheckResultController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ModelAndView("error_pages/error_page");
    }

}