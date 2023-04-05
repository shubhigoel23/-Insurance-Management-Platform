package com.InsuranceManagementPlatform.Insurance.Management.Platform.Model;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
//import java.text.SimpleDateFormat;
import java.util.*;

public class Claim {
    private Long id;
    private String claimNumber;
    private String description;
    private Date claimDate;
    private String claimStatus;
    @ManyToOne
    @JoinColumn(name = "insurance_policy_id")
    private InsurancePolicy insurancePolicy;


    // Constructor, getters and setters
    Object claimService;
    @GetMapping("/api/claims")
    public List<Claim> getClaims() {

        return claimService.getAllClaims();
    }
    @GetMapping("/api/claims/{id}")
    public Claim getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }
    @PostMapping("/api/claims")
    public Claim createClaim(@RequestBody Claim claim) {
        return claimService.createClaim(claim);
    }
    @PutMapping("/api/claims/{id}")
    public Claim updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        return claimService.updateClaim(id, claim);
    }
    @DeleteMapping("/api/claims/{id}")
    public void
    @Repository
    public interface ClaimRepository extends JpaRepository<Claim, Long> {
    }
}
