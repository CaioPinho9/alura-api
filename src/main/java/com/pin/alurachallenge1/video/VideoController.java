package com.pin.alurachallenge1.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(videoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        Optional<Video> videoOptional = videoService.findById(id);
        if (videoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(videoOptional.get());
    }

    @PostMapping("")
    public ResponseEntity<?> createVideo(@RequestBody VideoRequest videoRequest) {
        return ResponseEntity.ok(videoService.save(Video.convert(videoRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody VideoRequest videoRequest) {
        Optional<Video> videoOptional = videoService.findById(id);
        if (videoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }
        Video video = Video.autoComplete(videoOptional.get(), Video.convert(videoRequest));
        return ResponseEntity.status(HttpStatus.OK).body(videoService.save(video));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Video> videoOptional = videoService.findById(id);
        if (videoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }
        videoService.delete(videoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Video deleted successfully.");
    }
}
