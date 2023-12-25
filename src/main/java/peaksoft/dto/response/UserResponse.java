package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(

        Long id,
        String email,
        String token

){
}
