package peaksoft.api;

import peaksoft.dto.request.AuthRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.AuthResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.services.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "UserApi")
public class UserApi {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Sign Up", description = "To sign up fill all the fields!")
    public ResponseEntity<SimpleResponse> signUp(@RequestBody @Valid @ApiParam(value = "User registration request") UserRequest userRequest) {
        return ResponseEntity.ok(userService.signUp(userRequest));

    }



    @PostMapping("/signIn")
    @Operation(summary = "Sign In",description = "To sign in fill all the fields!")
    public AuthResponse signIn(@RequestBody @Valid AuthRequest signInRequest){
        return userService.signIn(signInRequest);
    }

//
//    @GetMapping("/{id}")
//    public UserResponse getUserById(@PathVariable Long id){
//        return userService.getUserById(id);
//    }

}
