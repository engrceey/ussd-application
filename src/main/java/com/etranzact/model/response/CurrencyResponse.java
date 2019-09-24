package com.etranzact.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
public class CurrencyResponse {

    private String base;
    private String date;
    @JsonProperty("time_last_updated")
    private Long timeLastUpdated;
    private CurrencyRate rate;
}
