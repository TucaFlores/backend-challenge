package com.myhotel.template.services;
import com.myhotel.template.entities.ActivityMails;
import com.myhotel.template.models.MessageResult;
import com.myhotel.template.repositories.ActivityMailsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SurveyNotificationService {

    private final EmailSenderService emailSenderService;
    private final EmailTemplateService emailTemplateService;
    private final ActivityMailsRepository activityMailsRepository;

    public SurveyNotificationService(EmailSenderService emailSenderService, EmailTemplateService emailTemplateService,  ActivityMailsRepository activityMailsRepository) {
        this.emailSenderService = emailSenderService;
        this.emailTemplateService = emailTemplateService;
        this.activityMailsRepository = activityMailsRepository;
    }

    public MessageResult notifyGuest(Long surveyResponseId) {
        String from = emailTemplateService.getSenderName(surveyResponseId);
        String to = emailTemplateService.getRecipe(surveyResponseId);
        String subject = "Thank you for your feedback!";
        String body = emailTemplateService.buildMailMessage(surveyResponseId);

        MessageResult messageResult = emailSenderService.sendEmail(from, to, subject, body);

        ActivityMails activityMails = new ActivityMails();
        activityMails.setSurveyId(surveyResponseId);
        activityMails.setFrom(from);
        activityMails.setTo(to);
        activityMails.setSubject(subject);
        activityMails.setMessage(body);
        activityMails.setSentAt(LocalDateTime.now());
        activityMailsRepository.save(activityMails);

        return messageResult;
    }

    public List<ActivityMails> getEmailHistory(List<Long> surveyIds) {
        if (surveyIds != null && !surveyIds.isEmpty()) {
            return activityMailsRepository.findAllBySurveyIdIn(surveyIds);
        } else {
            return activityMailsRepository.findAll();
        }
    }
}
