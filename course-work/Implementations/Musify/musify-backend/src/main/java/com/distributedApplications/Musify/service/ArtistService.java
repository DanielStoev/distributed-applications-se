package com.distributedApplications.Musify.service;

import com.distributedApplications.Musify.converter.ArtistMapper;
import com.distributedApplications.Musify.dto.ArtistDTO;
import com.distributedApplications.Musify.entity.Artist;
import com.distributedApplications.Musify.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistMapper artistMapper;

    public Page<ArtistDTO> getAllArtists(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return artistRepository.findAll(pageable).map(artistMapper::convertToDTO);
    }

    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        Artist artist = artistMapper.convertToEntity(artistDTO);
        artist = artistRepository.save(artist);
        return artistMapper.convertToDTO(artist);
    }

    public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO) {
        Optional<Artist> artist = artistRepository.findById(id);
        Artist artistEntity = new Artist();

        if (artist.isPresent()) {
            artistEntity = artist.get();
            artistEntity.setName(artistDTO.getName());
            artistEntity.setCountry(artistDTO.getCountry());
            artistEntity.setBirthDate(artistDTO.getBirthDate());
            artistRepository.save(artistEntity);
        }

        return artistMapper.convertToDTO(artistEntity);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}