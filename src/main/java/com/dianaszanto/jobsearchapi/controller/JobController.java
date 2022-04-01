package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.Job;
import com.dianaszanto.jobsearchapi.model.data.JobSuccessResponseDto;
import com.dianaszanto.jobsearchapi.service.JobService;
import com.dianaszanto.jobsearchapi.thirdparty.JobApiServiceImpl;
import com.dianaszanto.jobsearchapi.thirdparty.JobSearchResult;
import com.dianaszanto.jobsearchapi.thirdparty.Location;
import com.dianaszanto.jobsearchapi.thirdparty.Result;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Validated
public class JobController {
    private final JobService jobService;
    private final JobApiServiceImpl jobApiService;

    public JobController(JobService jobService, JobApiServiceImpl jobApiService) {
        this.jobService = jobService;
        this.jobApiService = jobApiService;
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
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/positions/{country}/{page}")
    public ResponseEntity<Set<Result>> getJobs(@PathVariable String country,
                                               @PathVariable Integer page,
                                               @RequestParam(required = false) @Size(max = 50) String location,
                                               @RequestParam(required = false) @Size(max = 50) String keyword)
            throws IOException {

        JobSearchResult resultsFromApi = jobApiService.showJobsByLocationAndKeyWord(country, page, keyword,
                                                                                    location);

        List<Job> allByLocationAndKeywordFromDb = jobService.findAllByLocationAndKeyword(location, keyword);
        List<Result> resultsFromDb =
                allByLocationAndKeywordFromDb.stream().map(j -> new Result(j.getTitle()
                        , new Location(j.getLocation()), j.getUrl().toString())).collect(Collectors.toList());

        resultsFromApi.getResults().addAll(resultsFromDb);

        Set<Result> resultsFromApiAndDb = new HashSet<>(resultsFromApi.getResults());
        return ResponseEntity.ok(resultsFromApiAndDb);
    }
}
