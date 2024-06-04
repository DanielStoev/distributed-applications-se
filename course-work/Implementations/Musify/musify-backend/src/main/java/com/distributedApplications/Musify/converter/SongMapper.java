package com.distributedApplications.Musify.converter;

import com.distributedApplications.Musify.dto.SongDTO;
import com.distributedApplications.Musify.entity.Song;
import com.distributedApplications.Musify.repository.AlbumRepository;
import com.distributedApplications.Musify.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public SongDTO convertToDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        songDTO.setDuration(song.getDuration());
        songDTO.setGenre(song.getGenre());
        songDTO.setReleaseDate(song.getReleaseDate());
        songDTO.setArtistId(song.getArtist().getId());
        songDTO.setAlbumId(song.getAlbum().getId());
        return songDTO;
    }

    public Song convertToEntity(SongDTO songDTO) {
        Song song = new Song();

        song.setId(songDTO.getId());
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setGenre(songDTO.getGenre());
        song.setReleaseDate(songDTO.getReleaseDate());
        song.setArtist(artistRepository.findById(songDTO.getArtistId()).isPresent() ? artistRepository.findById(songDTO.getArtistId()).get() : null);
        song.setAlbum(albumRepository.findById(songDTO.getAlbumId()).isPresent() ? albumRepository.findById(songDTO.getAlbumId()).get() : null);
        return song;
    }
}
