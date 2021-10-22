package com.project.scheduler.Controller;


import com.project.scheduler.service.AdminService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);
    Marker myMarker = MarkerFactory.getMarker("AdminClassMarker");
    @Autowired
    AdminService adminService;


    @GetMapping("/admin/{id}")
    public String clientMCDRequest(@PathVariable String id) throws InterruptedException {
        MDC.put("adminId", id);

        logger.info(myMarker, "admins {} has made a request", id);
        logger.info(myMarker, "Starting request");
        Thread.sleep(5000);
        logger.info(myMarker, "Finished request");
        MDC.clear();
        return "finished";
    }
}
