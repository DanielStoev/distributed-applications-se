package com.distributedApplications.Musify.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AlbumDTO {
    private Long id;

    private String title;

    private int numberOfSongs;

    private Date releaseDate;

    private Long artistId;

    private List<Integer> songIds = new ArrayList<>();
}
