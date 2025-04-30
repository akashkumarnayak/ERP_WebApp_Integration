package org.integration.erp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name="erp_user")
@Data
public class User {

    @Id
    String username;
    String firstName;
    String lastName;
    String alias;
    String emailId;

}
