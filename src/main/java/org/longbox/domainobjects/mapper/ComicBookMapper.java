package org.longbox.domainobjects.mapper;

import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;

import java.util.List;
import java.util.stream.Collectors;

public class ComicBookMapper {

    public static ComicBookDto toDto(ComicBook entity) {
        ComicBookDto dto = new ComicBookDto();
        dto.setId(entity.getId());
        dto.setSeriesTitle(entity.getSeriesTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setArtist(entity.getArtist());
        dto.setGenres(entity.getGenres() != null ? GenreUtils.genreStringToList(entity.getGenres()) : new String[0]);
        dto.setDescription(entity.getDescription());
        dto.setNumberOfIssues(entity.getNumberOfIssues());
        dto.setPublisher(entity.getPublisher());
        dto.setYearPublished(entity.getYearPublished());
        dto.setDateAdded(entity.getDateAdded());
        dto.setFavouritesCount(entity.getFavouritesCount());
        dto.setNorthAmericaFavouritesCount((entity.getNorthAmericaFavouritesCount()));
        dto.setSouthAmericaFavouritesCount((entity.getSouthAmericaFavouritesCount()));
        dto.setEuropeFavouritesCount((entity.getEuropeFavouritesCount()));
        dto.setAsiaFavouritesCount((entity.getAsiaFavouritesCount()));
        dto.setAfricaFavouritesCount((entity.getAfricaFavouritesCount()));
        dto.setOceaniaFavouritesCount((entity.getOceaniaFavouritesCount()));
        dto.setAntarcticaFavouritesCount((entity.getAntarcticaFavouritesCount()));
        return dto;
    }

    public static List<ComicBookDto> toDtoList(List<ComicBook> entityList) {
        return entityList.stream()
                .map(ComicBookMapper::toDto)
                .collect(Collectors.toList());
    }

    public static ComicBook toEntity(ComicBookDto dto) {
        ComicBook entity = new ComicBook();
        entity.setSeriesTitle(dto.getSeriesTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setArtist(dto.getArtist());
        entity.setGenres(GenreUtils.genreListToString(dto.getGenres()));
        entity.setDescription(dto.getDescription());
        entity.setNumberOfIssues(dto.getNumberOfIssues());
        entity.setPublisher(dto.getPublisher());
        entity.setYearPublished(dto.getYearPublished());
        entity.setDateAdded(dto.getDateAdded());
        entity.setFavouritesCount(dto.getFavouritesCount());
        entity.setNorthAmericaFavouritesCount(dto.getNorthAmericaFavouritesCount());
        entity.setSouthAmericaFavouritesCount((dto.getSouthAmericaFavouritesCount()));
        entity.setEuropeFavouritesCount((dto.getEuropeFavouritesCount()));
        entity.setAsiaFavouritesCount((dto.getAsiaFavouritesCount()));
        entity.setAfricaFavouritesCount((dto.getAfricaFavouritesCount()));
        entity.setOceaniaFavouritesCount((dto.getOceaniaFavouritesCount()));
        entity.setAntarcticaFavouritesCount((dto.getAntarcticaFavouritesCount()));
        return entity;
    }

    public static List<ComicBook> toEntityList(List<ComicBookDto> dtoList) {
        return dtoList.stream()
                .map(ComicBookMapper::toEntity)
                .collect(Collectors.toList());
    }
}
