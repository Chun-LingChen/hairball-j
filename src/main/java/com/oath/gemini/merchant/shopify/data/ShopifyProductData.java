package com.oath.gemini.merchant.shopify.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.oath.gemini.merchant.feed.ProductConstant;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("products")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopifyProductData {

    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String title;

    @JsonProperty(value = "body_html", required = true)
    private String description;

    @JsonIgnore
    private String link;

    @JsonIgnore
    private ProductConstant.ConditionEnum condition = ProductConstant.ConditionEnum.NEW;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("vendor")
    private String brand;
}
