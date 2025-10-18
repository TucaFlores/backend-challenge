package com.myhotel.template.repositories;

import com.myhotel.template.entities.ActivityMails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMailsRepository extends JpaRepository<ActivityMails, Long> {
    List<ActivityMails> findAllBySurveyIdIn(List<Long> surveyIds);
}
