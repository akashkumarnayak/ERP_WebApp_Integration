package org.integration.erp.dtos;

import lombok.Data;

@Data
public class UserResponseDto {
    String username;
    String firstName;
    String lastName;
    String alias;
    String emailId;
}
