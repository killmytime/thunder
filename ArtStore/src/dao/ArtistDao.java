package dao;

import entity.Artist;

import java.util.List;

public interface ArtistDao {
    List<Artist> getAll();

    Artist getOne(int ArtistID);

    List<Integer> getArtistByfilter(String keyWord);
}
