package com.distributedApplications.Musify.repository;

import com.distributedApplications.Musify.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Page<Artist> findAll(Pageable pageable);
}