package com.distributedApplications.Musify.repository;

import com.distributedApplications.Musify.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Page<Album> findAll(Pageable pageable);
}