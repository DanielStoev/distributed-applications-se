package com.distributedApplications.Musify.controller;

import com.distributedApplications.Musify.dto.AlbumDTO;
import com.distributedApplications.Musify.service.AlbumService;
import com.distributedApplications.Musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @GetMapping
    public Page<AlbumDTO> getAllAlbums(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return albumService.getAllAlbums(page, size);
    }

    @GetMapping("/getNumberOfSongs/{id}")
    public Integer getNumberOfSongs(@PathVariable Long id) {
        return songService.getNumberOfSongs(id);
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        AlbumDTO createdAlbum = albumService.createAlbum(albumDTO);
        return ResponseEntity.ok(createdAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Long id, @RequestBody AlbumDTO albumDTO) {
        AlbumDTO updatedAlbum = albumService.updateAlbum(id, albumDTO);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
