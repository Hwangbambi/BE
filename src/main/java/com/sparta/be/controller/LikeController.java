package com.sparta.be.controller;

import com.sparta.be.dto.LikeResponseDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    // ResponseEntity 구조 : HttpStatus, HttpHeaders, HttpBody
    @PostMapping("/post/{postId}/like")
    private ResponseEntity<?> likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likePost(postId, userDetails.getUsername());
    }
}
