package com.dianaszanto.jobsearchapi.service;

import com.dianaszanto.jobsearchapi.model.data.Job;

import java.util.List;

public interface JobService {
    void save(Job job);

    List<Job> findAllByLocationAndKeyword(String location, String keyWord);

    List<Job> findAllByKeyWord(String keyWord);
}
