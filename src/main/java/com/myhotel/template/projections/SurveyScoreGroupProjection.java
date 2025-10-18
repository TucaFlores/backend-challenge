package com.myhotel.template.projections;

import java.util.Optional;

public interface SurveyScoreGroupProjection {

    Long DEFAULT_VAL = 1L;

    String getHotelName();
    Long getHotelId();
    Long getScore();
    Long getWeight();

    default double weightedScore() {
        Long score = Optional.ofNullable(getScore()).orElse(DEFAULT_VAL);
        Long weight = Optional.ofNullable(getWeight()).orElse(DEFAULT_VAL);
        return score * weight;
    }
}
