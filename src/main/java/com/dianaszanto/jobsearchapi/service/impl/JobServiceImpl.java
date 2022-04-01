package com.dianaszanto.jobsearchapi.service.impl;

import com.dianaszanto.jobsearchapi.model.data.Job;
import com.dianaszanto.jobsearchapi.repository.JobRepository;
import com.dianaszanto.jobsearchapi.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public List<Job> findAllByLocationAndKeyword(String location, String keyword) {

        if (location == null || keyword == null) {
            return jobRepository.findAll();
        }

        List<Job> allByKeyWord = this.findAllByKeyWord(keyword);
        List<Job> resultList = new ArrayList<>();
        allByKeyWord.forEach((j) -> {
            if (j.getLocation().contains(location)) {
                resultList.add(j);
            }
        });

        return resultList;
    }

    @Override
    public List<Job> findAllByKeyWord(String keyWord) {
        if (keyWord == null) {
            return jobRepository.findAll();
        }

        return jobRepository.findAll().stream()
                .filter(j -> j.getTitle().toLowerCase(Locale.ROOT).contains(keyWord.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
