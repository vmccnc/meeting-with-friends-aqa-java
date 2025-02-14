package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Registration {

    private final String name;
    private final String surname;
    private final String username;
    private final String password;
    private final String birthdate;
    private final String email;
    private final String telephone;
    private final String photo;
}