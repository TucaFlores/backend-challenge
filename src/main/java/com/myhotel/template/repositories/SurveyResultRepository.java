package com.myhotel.template.repositories;

import com.myhotel.template.entities.SurveyResponse;
import com.myhotel.template.projections.EmailTemplateDataProjection;
import com.myhotel.template.projections.SurveyScoreGroupProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyResultRepository extends JpaRepository<SurveyResponse, Long> {

    /**
     * This query (if it works) retrieves the necessary data for the email template
     * based on the survey result ID.
     */
    @Query(value = """
    SELECT
        g.id AS guestId,
        g.name AS guestName,
        g.email AS guestEmail,
        h.name AS hotelName
    FROM
        guests g
    INNER JOIN
        surveys s ON s.guest_id = g.id
    INNER JOIN
        hotels h ON s.hotel_id = h.id
    WHERE
        s.id = :surveyResultId
    """, nativeQuery = true)
    EmailTemplateDataProjection findEmailTemplateDataBySurveyResultId(Long surveyResultId);

    /**
    * This query retrieves the survey scores grouped by hotel and score.
    */
    @Query(value = """
    SELECT
        h.name AS hotelName,
        s.hotel_id AS hotelId,
        s.score AS score,
        COUNT(*) AS scoreCant
    FROM
        surveys s
    INNER JOIN
        hotels h ON s.hotel_id = h.id
    GROUP BY
        s.hotel_id, s.score
    """, nativeQuery = true)
    List<SurveyScoreGroupProjection> findGroupedScoreData();
}
