package service.impl;

import dao.ArtistDao;
import dao.ArtworkDao;
import dao.DaoFactory.DaoFactory;
import dao.OrderDao;
import entity.Artwork;
import service.OrderService;
import service.SearchService;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    ArtworkDao artworkDao = DaoFactory.getArtworkDaoInstance();
    ArtistDao artistDao = DaoFactory.getArtistDaoInstance();

    @Override
    public List<Artwork> filterByTitle(String keyWord) {
        return artworkDao.filterByTitle(keyWord);
    }

    @Override
    public List<Artwork> filterByDescription(String keyWord) {
        return artworkDao.filterByDescription(keyWord);
    }

    @Override
    public List<Artwork> filterByArtist(String keyWord) {
        return artworkDao.filterByArtist(keyWord);
    }
}

