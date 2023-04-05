package com.InsuranceManagementPlatform.Insurance.Management.Platform.Model;

import jakarta.persistence.*;
import org.intellij.lang.annotations.Pattern;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String contactInformation;


        @NotBlank
        @Size(min = 2, max = 50)
        private String name;

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateOfBirth;

        @NotBlank
        private String address;

        @NotBlank
        @Pattern(regexp = "\\d{10}")
        private String contactNumber;


    // Constructor, getters and setters
    @RestController
    @RequestMapping("/api/clients")
    public class ClientController {

        @Autowired
        private ClientRepository clientRepository;

        // GET /api/clients
        @GetMapping
        public List<Client> getAllClients() {
            return clientRepository.findAll();
        }

        // GET /api/clients/{id}
        @GetMapping("/{id}")
        public ResponseEntity<Client> getClientById(@PathVariable Long id) {
            Optional<Client> optionalClient = clientRepository.findById(id);
            if (optionalClient.isPresent()) {
                return ResponseEntity.ok(optionalClient.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // POST /api/clients
        @PostMapping
        public ResponseEntity<Client> createClient(@RequestBody Client client) {
            Client savedClient = clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
        }

        // PUT /api/clients/{id}
        @PutMapping("/{id}")
        public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
            Optional<Client> optionalClient = clientRepository.findById(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                client.setName(updatedClient.getName());
                client.setDateOfBirth(updatedClient.getDateOfBirth());
                client.setAddress(updatedClient.getAddress());
                client.setContactInformation(updatedClient.getContactInformation());
                Client savedClient = clientRepository.save(client);
                return ResponseEntity.ok(savedClient);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // DELETE /api/clients/{id}
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
            Optional<Client> optionalClient = clientRepository.findById(id);
            if (optionalClient.isPresent()) {
                clientRepository.delete(optionalClient.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
    @Repository
    public interface ClientRepository extends JpaRepository<Client, Long> {
    }
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        return ResponseEntity.ok(clientRepository.save(client));
    }

    public class ClientNotFoundException extends RuntimeException {
        public ClientNotFoundException(String message) {
            super(message);
        }
    }


}
