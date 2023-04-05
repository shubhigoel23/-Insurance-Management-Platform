package com.InsuranceManagementPlatform.Insurance.Management.Platform.Model;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
//import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String policyNumber;
    private String type;
    private double coverageAmount;
    private double premium;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

//    private Client client;

    // Constructor, getters and setters
    @GetMapping("/api/policies")
    public List<InsurancePolicy> getPolicies() {
        return policyService.getAllPolicies();
    }
    InsurancePolicy policyService;
    @GetMapping("/api/policies/{id}")
    public InsurancePolicy getPolicyById(@PathVariable Long id) {

        return policyService.getPolicyById(id);
    }
    @PostMapping("/api/policies")
    public InsurancePolicy createPolicy(@RequestBody InsurancePolicy policy) {
        return policyService.createPolicy(policy);
    }

    @PutMapping("/api/policies/{id}")
    public InsurancePolicy updatePolicy(@PathVariable Long id, @RequestBody InsurancePolicy policy) {
        return policyService.updatePolicy(id, policy);
    }
    @DeleteMapping("/api/policies/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }
    @Repository
    public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    }



}
