package com.dianaszanto.jobsearchapi.thirdparty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @SerializedName("__CLASS__")
    @Expose
    @JsonIgnore
    private String _class;
    @SerializedName("label")
    @Expose
    @JsonIgnore
    private String label;
    @SerializedName("tag")
    @Expose
    @JsonIgnore
    private String tag;
}
