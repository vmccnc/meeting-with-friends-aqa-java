package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ContactRegistration {

    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String phone;
}