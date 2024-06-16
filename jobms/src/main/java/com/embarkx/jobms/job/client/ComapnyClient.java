package com.embarkx.jobms.job.client;

import com.embarkx.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS")
public interface ComapnyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable Long id);

}
