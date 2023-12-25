package peaksoft.dto.request;

import peaksoft.entities.Comment;

import java.time.LocalDate;

public record CommentRequest(
         String comment,

         LocalDate createdEt
) {
    public Comment build() {
       Comment comment1 = new Comment();
        comment1.setComment(this.comment);
         comment1.setCreatedEt(this.createdEt);
        return comment1;
    }
}
