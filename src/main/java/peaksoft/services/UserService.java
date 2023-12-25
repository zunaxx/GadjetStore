package peaksoft.services;

import peaksoft.dto.request.AuthRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.AuthResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;

public interface UserService {

    void init();

    SimpleResponse signUp(UserRequest userRequest);

    AuthResponse signIn(AuthRequest request);

//    UserResponse getUserById(Long id);

}
