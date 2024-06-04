package com.distributedApplications.Musify.repository;

import com.distributedApplications.Musify.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository extends JpaRepository<Song, Long> {

    Integer countByAlbumId(Long albumId);

}