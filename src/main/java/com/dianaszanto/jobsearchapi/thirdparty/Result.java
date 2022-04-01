package com.dianaszanto.jobsearchapi.thirdparty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("salary_is_predicted")
    @Expose
    @JsonIgnore
    private String salaryIsPredicted;
    @SerializedName("created")
    @Expose
    @JsonIgnore
    private String created;
    @SerializedName("__CLASS__")
    @Expose
    @JsonIgnore
    private String _class;
    @SerializedName("salary_min")
    @Expose
    @JsonIgnore
    private Double salaryMin;
    @SerializedName("company")
    @Expose
    @JsonIgnore
    private Company company;
    @SerializedName("redirect_url")
    @Expose
    private String redirectUrl;
    @SerializedName("id")
    @Expose
    @JsonIgnore
    private String id;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("adref")
    @Expose
    @JsonIgnore
    private String adref;
    @SerializedName("contract_type")
    @Expose
    @JsonIgnore
    private String contractType;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    @JsonIgnore
    private String description;
    @SerializedName("salary_max")
    @Expose
    @JsonIgnore
    private Double salaryMax;
    @SerializedName("category")
    @Expose
    @JsonIgnore
    private Category category;
    @SerializedName("longitude")
    @Expose
    @JsonIgnore
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    @JsonIgnore
    private Double latitude;

    public Result(String redirectUrl, Location location, String title) {
        this.redirectUrl = redirectUrl;
        this.location = location;
        this.title = title;
    }
}
