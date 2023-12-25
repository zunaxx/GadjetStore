package peaksoft.services;

import peaksoft.dto.request.CommentRequest;
import peaksoft.dto.response.CommentResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> findAllComments();

    CommentResponse save(CommentRequest commentRequest,Long productId);

    CommentResponse findCommentById(Long id);

//    SimpleResponse updateComment(String comment, CommentRequest commentRequest);


    SimpleResponse update(Long id, CommentRequest commentRequest);

    SimpleResponse deleteComment(Long id);
}
