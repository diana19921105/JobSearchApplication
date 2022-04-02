package com.dianaszanto.jobsearchapi.service.impl;

import com.dianaszanto.jobsearchapi.model.data.Job;
import com.dianaszanto.jobsearchapi.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceImplTest {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;


    @Test
    public void save() {
        Job job = new Job();
        when(jobRepository.save(any())).thenReturn(job);

        jobService.save(job);

        verify(jobRepository,times(1)).save(any());
    }
}