package com.oe.irrigation.service;

import com.oe.irrigation.entity.Plot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    private final EmailService emailService;

    @Autowired
    public AlertService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendSensorUnavailableAlert(Plot plot) {
        String message = "Sensor device is not available for plot: " + plot.getId();
        String recipientEmail = plot.getSupervisorEmail(); // Assuming the plot is associated with a user

        emailService.sendEmail(recipientEmail, "Sensor Unavailable Alert", message);
    }
}
