package service.impl;

import dao.ArtworkDao;
import dao.DaoFactory.DaoFactory;
import entity.Artwork;
import service.ArtworkService;

import java.util.List;

public class ArtworkServiceImpl implements ArtworkService {
    private ArtworkDao artworkDao;

    public ArtworkServiceImpl() {
        artworkDao = DaoFactory.getArtworkDaoInstance();
    }

    @Override
    public List<Artwork> carouselImg() {
        List<Artwork> artworks = artworkDao.sortedByClick();
        return artworks;
    }

    @Override
    public List<Artwork> newImg() {
        List<Artwork> artworks = artworkDao.sortedByNew();
        return artworks;
    }

    @Override
    public List<Artwork> getAll() {
        List<Artwork> artworks = artworkDao.getAll();
        return artworks;
    }

    @Override
    public List<Artwork> getByArtist(int ArtistID) {
        List<Artwork> artworks = artworkDao.sortedInArtist(ArtistID);
        return artworks;
    }

    @Override
    public Artwork getOne(int ArtworkID) {
        Artwork artwork = artworkDao.getOne(ArtworkID);
        return artwork;
    }

    @Override
    public void update(int artworkID, int clickNumber) {
        artworkDao.update(artworkID, clickNumber);
    }

    @Override
    public void update(Artwork artwork) {
        artworkDao.update(artwork);
    }
}
