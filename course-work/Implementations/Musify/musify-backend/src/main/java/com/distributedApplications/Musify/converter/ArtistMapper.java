package com.distributedApplications.Musify.converter;

import com.distributedApplications.Musify.dto.ArtistDTO;
import com.distributedApplications.Musify.entity.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    public ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setName(artist.getName());
        artistDTO.setCountry(artist.getCountry());
        artistDTO.setBirthDate(artist.getBirthDate());
        return artistDTO;
    }

    public Artist convertToEntity(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setId(artistDTO.getId());
        artist.setName(artistDTO.getName());
        artist.setCountry(artistDTO.getCountry());
        artist.setBirthDate(artistDTO.getBirthDate());
        return artist;
    }
}
