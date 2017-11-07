package service;

import entity.Artwork;

import java.util.List;

public interface SearchService {

    //By Artwork
    List<Artwork> filterByTitle(String keyWord);

    //By Description
    List<Artwork> filterByDescription(String keyWord);

    //By Artist
    List<Artwork> filterByArtist(String keyWord);

}
