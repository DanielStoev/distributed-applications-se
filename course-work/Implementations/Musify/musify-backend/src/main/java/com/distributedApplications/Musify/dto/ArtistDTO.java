package com.distributedApplications.Musify.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ArtistDTO {

    private Long id;

    private String name;

    private List<Integer> albumIds = new ArrayList<>();

    private List<Integer> songIds = new ArrayList<>();

    private String country;

    private Date birthDate;
}
