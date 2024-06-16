package com.embarkx.jobms.job.impl;


import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.JobRepository;
import com.embarkx.jobms.job.JobService;
import com.embarkx.jobms.job.client.ComapnyClient;
import com.embarkx.jobms.job.client.ReviewClient;
import com.embarkx.jobms.job.dto.JobDTO;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;
import com.embarkx.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;
    private ComapnyClient companyClient ;
    private ReviewClient reviewClient ;
    public JobServiceImpl(JobRepository jobRepository , ComapnyClient comapnyClient , ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = comapnyClient;
        this.reviewClient = reviewClient;
    }
//@Retry(name = "companyBreaker" , fallbackMethod = "fallBackMethod")
//@CircuitBreaker(name = "companyBreaker" , fallbackMethod = "fallBackMethod")

    @Override
    @RateLimiter(name = "companyBreakers"  , fallbackMethod = "fallBackMethod")
      public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOS.add(jobWithCompanyDTOMethod(job));
        }

        return jobDTOS;
    }

   public List<String> fallBackMethod(Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add("some error");
        return errors;
    }

    private JobDTO jobWithCompanyDTOMethod(Job job){
        JobDTO jobDTO = new JobDTO();
        JobMapper jobMapper = new JobMapper();

        Company company = companyClient.getCompany(job.getCompanyId());
         List<Review>  reviews =  new ArrayList<>();
               reviews =  reviewClient.getReviews(job.getCompanyId());

 return jobMapper.mapToJobWithCompanyDTO(job  , company , reviews);
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {

        return jobWithCompanyDTOMethod(jobRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}