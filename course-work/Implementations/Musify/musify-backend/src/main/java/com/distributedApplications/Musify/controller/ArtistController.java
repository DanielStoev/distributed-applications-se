package com.distributedApplications.Musify.controller;

import com.distributedApplications.Musify.dto.ArtistDTO;
import com.distributedApplications.Musify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public Page<ArtistDTO> getAllArtists(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return artistService.getAllArtists(page, size);
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        ArtistDTO createdArtist = artistService.createArtist(artistDTO);
        return ResponseEntity.ok(createdArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
        ArtistDTO updatedArtist = artistService.updateArtist(id, artistDTO);
        return ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}