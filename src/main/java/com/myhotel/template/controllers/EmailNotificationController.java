package com.myhotel.template.controllers;

import com.myhotel.template.entities.ActivityMails;
import com.myhotel.template.services.SurveyNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class EmailNotificationController {
    private final SurveyNotificationService notificationService;

    public EmailNotificationController(SurveyNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send/{surveyResponseId}")
    public ResponseEntity<?> sendNotification(@PathVariable Long surveyResponseId) {
        return ResponseEntity.ok(notificationService.notifyGuest(surveyResponseId));
    }

    @GetMapping("/mails/history")
    public ResponseEntity<List<ActivityMails>> getEmailHistory(
            @RequestParam(required = false) List<Long> surveyIds) {

        List<ActivityMails> emails = notificationService.getEmailHistory(surveyIds);
        return ResponseEntity.ok(emails);
    }
}
