package com.dianaszanto.jobsearchapi.thirdparty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Generated("jsonschema2pojo")
public class JobSearchResult {

        @SerializedName("count")
        @Expose
        @JsonIgnore
        private Integer count;
        @SerializedName("results")
        @Expose
        private List<Result> results = new ArrayList<>();
        @SerializedName("__CLASS__")
        @Expose
        @JsonIgnore
        private String _class;
        @SerializedName("mean")
        @Expose
        @JsonIgnore
        private Double mean;

}
