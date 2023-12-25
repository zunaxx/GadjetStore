package peaksoft.dto.response;

import peaksoft.enums.Role;
import lombok.Builder;

@Builder
public record AuthResponse(String token,
                           String email,
                           Role role) {
}
