package service;

import entity.Artwork;

import java.util.List;

public interface ArtworkService {
    List<Artwork> carouselImg();
    List<Artwork> newImg();
    List<Artwork> getAll();
    List<Artwork> getByArtist(int ArtistID);
    Artwork getOne(int ArtworkID);
    void update(int artworkID,int clickNumber);
    void update(Artwork artwork);
}
