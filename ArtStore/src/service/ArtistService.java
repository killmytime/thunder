package service;

import entity.Artist;
import util.PageBean;

import java.util.List;

public interface ArtistService {
    List<Artist> getAll();
    Artist getByArtwork(int ArtworkID);
    void getAll(PageBean<Artist> pb);
}
