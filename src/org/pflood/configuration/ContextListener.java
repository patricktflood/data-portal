/*
    P Flood 24/02/2013

    Event handler class for handling application scope events
    contextInitialized / contextDestroyed run when the application is deployed / undeployewd
 */

package org.pflood.configuration;

import javax.servlet.*;
import org.pflood.data.*;

public final class ContextListener implements ServletContextListener {

    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        // Create PaperDB object and save it as an attribute to
        // ServletContext scope object.
        try {
            SQLServerDBAO pf_data = new SQLServerDBAO();
            context.setAttribute("pf_data", pf_data);
        } catch (Exception ex) {
            System.out.println("Couldn't create Paper_Data database access object: " +
                    ex.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();

        SQLServerDBAO pf_data = (SQLServerDBAO) context.getAttribute("pf_data");

        if (pf_data != null) {
            pf_data.remove();
        }

        context.removeAttribute("pf_data");
    }
}
