package com.distributedApplications.Musify.converter;

import com.distributedApplications.Musify.dto.AlbumDTO;
import com.distributedApplications.Musify.entity.Album;
import com.distributedApplications.Musify.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {

    @Autowired
    private ArtistRepository artistRepository;

    public AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setTitle(album.getTitle());
        albumDTO.setNumberOfSongs(album.getNumberOfSongs());
        albumDTO.setReleaseDate(album.getReleaseDate());
        albumDTO.setArtistId(album.getArtist().getId());
        return albumDTO;
    }

    public Album convertToEntity(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setTitle(albumDTO.getTitle());
        album.setNumberOfSongs(albumDTO.getNumberOfSongs());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setArtist(artistRepository.findById(albumDTO.getArtistId()).isPresent() ? artistRepository.findById(albumDTO.getArtistId()).get() : null);
        return album;
    }
}
