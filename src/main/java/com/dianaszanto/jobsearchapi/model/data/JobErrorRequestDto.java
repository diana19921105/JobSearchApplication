package com.dianaszanto.jobsearchapi.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobErrorRequestDto {
    private String status = "error";
    private String message;

    public JobErrorRequestDto(String message) {
        this.message = message;
    }
}
