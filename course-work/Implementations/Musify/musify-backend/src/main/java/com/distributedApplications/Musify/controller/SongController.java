package com.distributedApplications.Musify.controller;

import com.distributedApplications.Musify.dto.SongDTO;
import com.distributedApplications.Musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public Page<SongDTO> getAllSongs(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return songService.getAllSongs(page, size);
    }

    @PostMapping
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO) {
        SongDTO createdSong = songService.createSong(songDTO);
        return ResponseEntity.ok(createdSong);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Long id, @RequestBody SongDTO songDTO) {
        SongDTO updatedSong = songService.updateSong(id, songDTO);
        return ResponseEntity.ok(updatedSong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
