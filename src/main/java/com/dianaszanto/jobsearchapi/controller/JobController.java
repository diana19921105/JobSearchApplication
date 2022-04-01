package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.Job;
import com.dianaszanto.jobsearchapi.model.data.JobSuccessResponseDto;
import com.dianaszanto.jobsearchapi.service.JobService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Size;
import java.net.URL;
import java.util.Objects;

@RestController
@Slf4j
@Validated
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/positions")
    public ResponseEntity<JobSuccessResponseDto> postPosition(@RequestParam @Size(max = 50) String title,
                                                              @RequestParam @Size(max = 50) String location) {
        try {
            URL url = Objects.requireNonNull(HttpUrl.parse("http://localhost:8080/positions?title=" + title +
                                                           "&location=" + location)).url();
            Job jobToSave = new Job(title, location, url);
            jobService.save(jobToSave);
            return ResponseEntity.ok(new JobSuccessResponseDto(url));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
