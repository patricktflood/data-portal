package org.pflood.spring32.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataExtractionController {

    @RequestMapping("data_extraction.do")
    public ModelAndView healthCheck() {
        String message = "";
        return new ModelAndView("data_extraction", "message", message);
    }

}
