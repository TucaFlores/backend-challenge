package com.myhotel.template.services;

import com.myhotel.template.projections.EmailTemplateDataProjection;
import com.myhotel.template.repositories.SurveyResultRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {

    private SurveyResultRepository surveyResultRepository;


    public EmailTemplateService(SurveyResultRepository surveyResultRepository) {
        this.surveyResultRepository = surveyResultRepository;
    }

    public EmailTemplateDataProjection getTemplateData(Long surveyResponseId) {
        return surveyResultRepository.findEmailTemplateDataBySurveyResultId(surveyResponseId);
    }

    public String getRecipe(Long surveyResponseId) {
        EmailTemplateDataProjection templateDataProjection =  this.getTemplateData(surveyResponseId);
        return templateDataProjection.getGuestEmail();
    }


    public String buildMailMessage(Long surveyResponseId) {
        EmailTemplateDataProjection templateDataProjection =  this.getTemplateData(surveyResponseId);
        return this.buildMailMessage(templateDataProjection);
    }

    public String buildMailMessage(EmailTemplateDataProjection templateData) {
        return String.format("Thanks %s for answering our survey. Kind regards, %s!",
                templateData.getGuestName(),
                templateData.getHotelName());
    }

    public String getSenderName(Long surveyResponseId) {
        EmailTemplateDataProjection templateData =  this.getTemplateData(surveyResponseId);
        return templateData.getHotelName();
    }
}
