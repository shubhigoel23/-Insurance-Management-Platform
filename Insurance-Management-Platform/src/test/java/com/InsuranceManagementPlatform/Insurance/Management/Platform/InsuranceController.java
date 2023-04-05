package com.InsuranceManagementPlatform.Insurance.Management.Platform;

import com.InsuranceManagementPlatform.Insurance.Management.Platform.Model.Claim;
import com.InsuranceManagementPlatform.Insurance.Management.Platform.Model.Client;
import com.InsuranceManagementPlatform.Insurance.Management.Platform.Model.InsurancePolicy;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceController {
    @Autowired
    private Client.ClientRepository clientRepository;

    @Autowired
    private InsurancePolicy.InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private Claim.ClaimRepository claimRepository;

    // Endpoints for clients

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));

        client.setName(clientDetails.getName());
        client.setDateOfBirth(clientDetails.getDateOfBirth());
        client.setAddress(clientDetails.getAddress());
        client.setContactInformation(clientDetails.getContactInformation());

        return clientRepository.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Client client = client

