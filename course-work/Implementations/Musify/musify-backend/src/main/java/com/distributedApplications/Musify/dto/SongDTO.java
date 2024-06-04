package com.distributedApplications.Musify.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SongDTO {

    private Long id;

    private String title;

    private Double duration;

    private String genre;

    private Date releaseDate;

    private Long artistId;

    private Long albumId;
}
