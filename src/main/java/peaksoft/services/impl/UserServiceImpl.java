package peaksoft.services.impl;

import peaksoft.dto.request.AuthRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.AuthResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.entities.User;
import peaksoft.enums.Role;
import peaksoft.repo.UserRepo;
import peaksoft.security.JwtService;
import peaksoft.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;




    @PostConstruct
    @Override
    public void init() {
        User user=new User();
         user.setRole(Role.ADMIN);
         user.setFirstName("Abubakir");
         user.setLastName("Boss");
         user.setEmail("admin@gmail.com");
         user.setCreatedEt(LocalDate.now());
         user.setUpdatedEt(LocalDate.now());
         user.setPassword(passwordEncoder.encode("admin"));
         if (!userRepo.existsByEmail("admin@gmail.com")){
              userRepo.save(user);
         }

    }


    @Override
    public SimpleResponse signUp(UserRequest userRequest) {
        User user = new User();
        user.setRole(Role.USER);
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setCreatedEt(LocalDate.now());
        user.setUpdatedEt(LocalDate.now());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userRequest.password()));

        if (!userRepo.existsByEmail(userRequest.email())) {
            userRepo.save(user);
            log.info("User successfully saved");
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully saved user with id: "+user.getId())
                    .build();
        } else {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Role is invalid!")
                    .build();
        }
    }

    @Override
    public AuthResponse signIn(AuthRequest request) {
        User user = userRepo.getUserByEmail(request.email()).orElseThrow(() ->
                new EntityNotFoundException("User with email: " + request.email() + " not found"));

      if (request.email().isBlank()){
          throw new BadCredentialsException("Email is blank");
      }
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }
        Role role = user.getRole();
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .token(jwtToken)
                .role(role)
                .build();
    }



}
