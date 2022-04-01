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
public class Location {
    @SerializedName("__CLASS__")
    @Expose
    @JsonIgnore
    private String _class;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("area")
    @Expose
    private List<String> area = new ArrayList<>();

    public Location(String displayName) {
        this.displayName = displayName;
    }
}
