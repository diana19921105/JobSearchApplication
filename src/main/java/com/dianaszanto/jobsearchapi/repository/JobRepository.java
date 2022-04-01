package com.dianaszanto.jobsearchapi.repository;

import com.dianaszanto.jobsearchapi.model.data.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
