package com.paradanomada.dto;

import java.util.List;

public class PlazaDtoListDto {

    private List<PlazaDto> plazaDtoList;

    public PlazaDtoListDto() {
    }

    public PlazaDtoListDto(List<PlazaDto> plazaDtoList) {
        this.plazaDtoList = plazaDtoList;
    }

    public List<PlazaDto> getPlazaDtoList() {
        return plazaDtoList;
    }

    public void setPlazaDtoList(List<PlazaDto> plazaDtoList) {
        this.plazaDtoList = plazaDtoList;
    }
}
