package com.crayon.model;

import com.crayon.validator.ValidSource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignSubscription {



    @ValidSource
    @JsonProperty("source")
    private String source;

    @JsonProperty("tracking_id")
    private String trackingId;

    @NotBlank(message =  "Name is required")
    @JsonProperty("name")
    private String name;

    @Valid
    @Min(value=3,message="Quantity must be minimum 3")
    @JsonProperty("quantity")
    private int  quantity;

    @Valid
    @NotNull
    @JsonProperty("customerTenant")
    private customerTenant customerTenant;

    @Valid
    @NotNull
    @JsonProperty("product")
    private Product product;

    @JsonProperty("billingCycle")
    private int  billingCycle;

    @NotBlank(message =  "TermDuration is required")
    @JsonProperty("termDuration")
    private String  termDuration;

}
