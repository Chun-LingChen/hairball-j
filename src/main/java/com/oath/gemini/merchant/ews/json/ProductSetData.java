package com.oath.gemini.merchant.ews.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.gemini.merchant.ews.EWSConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tong on 10/1/2017
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSetData {
    @JsonProperty(required = true)
    private Long id;

    @JsonProperty(required = true)
    private Long advertiserId;

    @JsonProperty(required = true)
    private EWSConstant.StatusEnum status;

    @JsonProperty(required = true)
    private String filter;
}
