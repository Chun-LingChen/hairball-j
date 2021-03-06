package com.oath.gemini.merchant.ews.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.gemini.merchant.ews.EWSConstant.StatusEnum;
import com.oath.gemini.merchant.ews.EWSConstant.PrdFeedFrequencyEnum;
import com.oath.gemini.merchant.ews.EWSConstant.PrdFeedTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tong on 10/1/2017
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFeedData {
    // It is returned after a feed is uploaded
    private Long id;

    @JsonProperty(required = true)
    private Long advertiserId;

    @JsonProperty(required = true)
    private StatusEnum status = StatusEnum.ACTIVE;

    @JsonProperty(required = true)
    private PrdFeedTypeEnum feedType;

    @JsonProperty(required = true)
    private String userName;

    // required during creation
    private String password;

    @JsonProperty(required = true)
    private String fileName;

    @JsonProperty(required = true)
    private String feedUrl;

    // It is optional if feed type=DPA_ONE_TIME
    private ScheduleInfo scheduleInfo;

    @Getter
    @Setter
    static class ScheduleInfo {
        private PrdFeedFrequencyEnum feedFrequency;
        private String dayOfMonth;
        private String dayOfWeek;
        private String hourMinute;
    }
}
