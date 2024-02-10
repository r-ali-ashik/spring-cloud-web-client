package com.rali.ws.dto;

import lombok.Data;

@Data
public class AlbumDto {
    private String userId;
    private String albumId;
    private String albumTitle;
    private String albumDescription;
    private String albumUrl;
}
