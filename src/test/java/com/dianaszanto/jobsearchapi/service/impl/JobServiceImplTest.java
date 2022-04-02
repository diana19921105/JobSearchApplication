package com.dianaszanto.jobsearchapi.service.impl;

import com.dianaszanto.jobsearchapi.model.data.Job;
import com.dianaszanto.jobsearchapi.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

        verify(jobRepository, times(1)).save(any());
    }

    @Test
    public void givenFindAllByKeyWord_whenKeyWordIsNull_thenReturnAll() throws MalformedURLException {
        List<Job> allJobs = new ArrayList<>();
        allJobs.add(new Job("title", "location", new URL("http://localhost:8080/positions")));
        allJobs.add(new Job("title1", "location1", new URL("http://localhost:8080/positions1")));

        when(jobRepository.findAll()).thenReturn(allJobs);

        List<Job> expected = jobService.findAllByKeyWord(null);

        assertEquals(expected.get(0).getTitle(), allJobs.get(0).getTitle());
        assertEquals(expected.get(0).getLocation(), allJobs.get(0).getLocation());
        assertEquals(expected.get(0).getUrl().toString(), allJobs.get(0).getUrl().toString());
        assertEquals(expected.get(1).getTitle(), allJobs.get(1).getTitle());
        assertEquals(expected.get(1).getLocation(), allJobs.get(1).getLocation());
        assertEquals(expected.get(1).getUrl().toString(), allJobs.get(1).getUrl().toString());

        verify(jobRepository, times(1)).findAll();
    }
}