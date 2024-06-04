package com.distributedApplications.Musify.converter;

import com.distributedApplications.Musify.dto.AlbumDTO;
import com.distributedApplications.Musify.entity.Album;
import com.distributedApplications.Musify.entity.Artist;
import com.distributedApplications.Musify.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlbumMapperTest {

    @InjectMocks
    private AlbumMapper albumMapper;

    @Mock
    private ArtistRepository artistRepository;

    private Album album = new Album();
    private AlbumDTO albumDTO = new AlbumDTO();
    private Artist artist = new Artist();

    @BeforeEach
    void setUp() {
        artist = new Artist();
        artist.setId(1L);

        album = new Album();
        album.setId(1L);
        album.setTitle("Test Album");
        album.setNumberOfSongs(10);
        album.setReleaseDate(Date.from(Instant.parse("2024-01-01T00:00:00Z")));
        album.setArtist(artist);

        albumDTO = new AlbumDTO();
        albumDTO.setId(1L);
        albumDTO.setTitle("Test Album");
        albumDTO.setNumberOfSongs(10);
        albumDTO.setReleaseDate(Date.from(Instant.parse("2024-01-01T00:00:00Z")));
        albumDTO.setArtistId(1L);
    }

    @Test
    void testConvertToDTO() {
        AlbumDTO result = albumMapper.convertToDTO(album);
        assertEquals(albumDTO.getId(), result.getId());
        assertEquals(albumDTO.getTitle(), result.getTitle());
        assertEquals(albumDTO.getNumberOfSongs(), result.getNumberOfSongs());
        assertEquals(albumDTO.getReleaseDate(), result.getReleaseDate());
        assertEquals(albumDTO.getArtistId(), result.getArtistId());
    }

    @Test
    void testConvertToEntity() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        Album result = albumMapper.convertToEntity(albumDTO);
        assertEquals(album.getId(), result.getId());
        assertEquals(album.getTitle(), result.getTitle());
        assertEquals(album.getNumberOfSongs(), result.getNumberOfSongs());
        assertEquals(album.getReleaseDate(), result.getReleaseDate());
        assertEquals(album.getArtist().getId(), result.getArtist().getId());
    }

    @Test
    void testConvertToEntityWithNullArtist() {
        albumDTO.setArtistId(2L);
        when(artistRepository.findById(2L)).thenReturn(Optional.empty());
        Album result = albumMapper.convertToEntity(albumDTO);
        assertEquals(albumDTO.getId(), result.getId());
        assertEquals(albumDTO.getTitle(), result.getTitle());
        assertEquals(albumDTO.getNumberOfSongs(), result.getNumberOfSongs());
        assertEquals(albumDTO.getReleaseDate(), result.getReleaseDate());
        assertNull(result.getArtist());
    }
}