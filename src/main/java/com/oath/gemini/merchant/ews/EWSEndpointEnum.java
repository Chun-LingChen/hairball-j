package com.oath.gemini.merchant.ews;

/**
 * @author tong on 10/1/2017
 */
public enum EWSEndpointEnum {
    ADVERTISER("advertiser"),

    // https://developer.yahoo.com/gemini/guide/campaigns.html
    CAMPAIGN_OPS("campaign"), CAMPAIGN_BY_ID("campaign/${0}"), CAMPAIGN_BY_ADVERTISER("campaign/?advertiserId=${0}"),

    ADGROUP_OPS("adgroup"), ADGROUP_BY_ID("adgroup/${0}"), ADGROUP_BY_CAMPAIGN("adgroup?campaignId=${0}"), ADGROUP_BY_ADVERTISER(
            "adgroup?advertiserId=${0}"),

    // https://developer.yahoo.com/gemini/guide/dynamic-product-ads/dpa-rules/
    PRODUCT_RULE_OPS("dparule"),

    // https://developer.yahoo.com/gemini/guide/dynamic-product-ads/operations-dpa/
    PRODUCT_FEED("feed"), PRODUCT_FEED_STATUS("feed/status?feedId=${0}"), PRODUCT_FEED_BY_ADVERTISER("feed?advertiserId=${0}"),

    // https://developer.yahoo.com/gemini/guide/dynamic-product-ads/dpa-product-sets/
    PRODUCT_SET_OPS("productset"), PRODUCT_SET_BY_ID("productset/${0}"), PRODUCT_SET_BY_ADVERTISER(
            "productset?advertiserId=${0}"), PRODUCT_SET_BY_ADGROUP("productset?adgroupId=${0}");
    private String url = "";

    EWSEndpointEnum(String subpath) {
        this(subpath, 3);
    }

    EWSEndpointEnum(String subpath, int version) {
        switch (version) {
        case 2:
            url = "https://api.gemini.yahoo.com/v2/rest/" + subpath;
            break;
        case 3:
            url = "https://api.gemini.yahoo.com/v3/rest/" + subpath;
            break;
        default:
            throw new IllegalArgumentException("Unknown EWS API version: " + version + "for " + subpath);
        }
    }

    @Override
    public String toString() {
        return url;
    }
}
