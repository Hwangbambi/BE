package com.sparta.be.controller;

import com.sparta.be.dto.CommentRequestDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postid}/comment")
    public ResponseEntity<?> createComment(@PathVariable Long postid, @RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.createComment(postid, commentRequestDto, userDetails.getUser());

    }

    @PatchMapping ("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<?> updateComment(@PathVariable Long postid ,@PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {//

        return  commentService.updateComment(postid, commentid, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<?> deleteComment(@PathVariable Long postid,@PathVariable Long commentid,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.deleteComment(postid,commentid, userDetails.getUser());
    }
}
