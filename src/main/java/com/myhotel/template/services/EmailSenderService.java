package com.myhotel.template.services;

import com.myhotel.template.config.EmailSenderProperties;
import com.myhotel.template.models.MessageResult;
import com.myhotel.template.projections.EmailTemplateDataProjection;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailSenderService {

    private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

    private final EmailSenderProperties properties;
    private final EmailTemplateService emailTemplateService;

    public EmailSenderService(EmailSenderProperties properties, EmailTemplateService emailTemplateService) {
        this.properties = properties;
        this.emailTemplateService = emailTemplateService;
    }

    public MessageResult sendEmail(String from, String to, String subject, String body) {

        log.info("Email preview: {}", body);

        int timeout = properties.getTimeoutSeconds();
        log.info("[EMAIL] Sending with timeout of {} seconds", timeout);

        try {
            Thread.sleep(timeout * 1000L);
        } catch (InterruptedException e) {
            log.error("Email sending interrupted", e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("Email sending interrupted", e);
        }

        return new MessageResult(from,subject,to,body);
    }

    public EmailTemplateDataProjection getTemplateData(Long surveyResponseId) {
        return emailTemplateService.getTemplateData(surveyResponseId);
    }
}
