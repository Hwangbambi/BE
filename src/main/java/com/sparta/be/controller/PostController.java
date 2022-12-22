package com.sparta.be.controller;

import com.sparta.be.dto.PostRequestDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.AwsS3Service;
import com.sparta.be.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final AwsS3Service awsS3Service;

    //게시글 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<?> posts() {
        return postService.posts();
    }
    @GetMapping("/tops")
    public ResponseEntity<?> Top() {
        return postService.Top();
    }


    //게시글 작성 및 파일 업로드
    @PostMapping("/post")
    public ResponseEntity<?> uploadFile(@RequestPart String title, @RequestPart String content, @RequestPart String category,
                                        @RequestPart(value = "imageUrl") MultipartFile multipartFile,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String imageUrl = null;

        if (!multipartFile.isEmpty()) {
            imageUrl = awsS3Service.uploadFile(multipartFile);
        }

        return postService.savePost(title, content, category, imageUrl, userDetails.getUser());
    }

    //게시글 상세 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<?> postDetailed(@PathVariable Long id) {
        return postService.postDetailed(id);
    }

    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> postDelete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.postDelete(id, userDetails.getUser());
    }

    /*게시글 수정
    @PatchMapping("post/{id}")
    public ResponseEntity<?> postUpdate(@PathVariable Long id, @RequestParam String title, @RequestParam String content, @RequestParam String category,
                                        @RequestPart(value = "file") MultipartFile multipartFile,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.postUpdate(id,title,content,category,multipartFile,userDetails.getUser());
    }*/

}
