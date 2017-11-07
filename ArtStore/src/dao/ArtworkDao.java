package dao;

import entity.Artwork;

import java.util.List;

public interface ArtworkDao {
    List<Artwork> getAll();

    Artwork getOne(int ArtWorkID);

    List<Artwork> sortedByClick();

    List<Artwork> sortedByNew();

    List<Artwork> sortedInArtist(int ArtistID);

    void update(int ArtworkID, int ClickNumber);

    void update(Artwork artwork);

    List<Artwork> filterByTitle(String keyWord);

    List<Artwork> filterByDescription(String keyWord);

    List<Artwork> filterByArtist(String keyWord);
}
