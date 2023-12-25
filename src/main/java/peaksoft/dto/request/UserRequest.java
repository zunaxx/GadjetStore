package peaksoft.dto.request;

import peaksoft.entities.User;
import jakarta.validation.constraints.Email;

public record UserRequest(String firstName,

                          String lastName,

                          @Email(message = "Email должен иметь все формат адреса @ .com")
                          String email,
                          String password
) {

    public User build(){
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
