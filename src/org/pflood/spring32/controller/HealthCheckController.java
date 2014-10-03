package org.pflood.spring32.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthCheckController {

    @RequestMapping(value={"app.do", "health_check.do"})
    public ModelAndView healthCheck() {
        String message = "";
        return new ModelAndView("health_check", "message", message);
    }

}
