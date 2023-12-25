package peaksoft.dto.response;

import lombok.Builder;

@Builder

public record CommentResponse(
        Long id,
        String comment
//        LocalDate createdAt
) {
    public CommentResponse {
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String comment() {
        return comment;
    }

    public String getComment() {
        return comment;
    }
}
