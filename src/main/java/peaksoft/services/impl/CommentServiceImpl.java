package peaksoft.services.impl;

import peaksoft.dto.request.CommentRequest;
import peaksoft.dto.response.CommentResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.entities.Comment;
import peaksoft.entities.Product;
import peaksoft.entities.User;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.CommentRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Override
    public List<CommentResponse> findAllComments() {
        return commentRepo.findAllComments();
    }

    @Override
    public CommentResponse save(CommentRequest commentRequest, Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.getUserByEmail(email).orElseThrow(() ->
                new RuntimeException("User with email: " + email + " not found"));
        Product product = productRepo.findProductById(productId).orElseThrow(() ->
                new NotFoundException("Product with id: " + productId + " not found"));
        Comment comment = commentRequest.build();
        comment.setProduct(product);
        user.addComment(comment);
        commentRepo.save(comment);
        log.info("Comment is saved");
        return CommentResponse.builder()
                .comment(comment.getComment())
                .build();
    }

    @Override
    public CommentResponse findCommentById(Long id) {
        return commentRepo.findCommentById(id);
    }


    @Override
    public SimpleResponse update(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepo.findById(id).orElseThrow(() ->
                new BadRequestException("Comment with id: " + id + "not found"));
        comment.setComment(commentRequest.comment());
        commentRepo.save(comment);
        log.info("Comment successfully updated");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Comment successfully updated")
                .build();
    }

    @Override
    public SimpleResponse deleteComment(Long id) {
       if (!commentRepo.existsById(id)){
          throw new NotFoundException(String.format("Comment with id: %s not found",id));
       }
       commentRepo.deleteById(id);
       log.info("Comment is deleted!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Comment is D e l e t e d")
                .build();
    }


//    @Override
//    public SimpleResponse updateComment(String comment, CommentRequest commentRequest) {
//        Comment comment1 = commentRepo.findByComment(comment).orElseThrow(() ->
//                new BadRequestException("Comment " + comment + " not found"));
//        comment1.setComment(commentRequest.comment());
//        commentRepo.save(comment1);
//        return SimpleResponse.builder()
//                .httpStatus(HttpStatus.OK)
//                .message("Comment successfully updated")
//                .build();
//    }


}
