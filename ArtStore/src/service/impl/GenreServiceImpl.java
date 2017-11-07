package service.impl;

import dao.ArtworkGenresDao;
import dao.DaoFactory.DaoFactory;
import dao.GenreDao;
import entity.Genre;
import service.GenreService;

public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;
    private ArtworkGenresDao artworkGenresDao;
    public GenreServiceImpl(){
        genreDao= DaoFactory.getGenreDaoInstance();
        artworkGenresDao=DaoFactory.getArtworkGenresDaoInstance();
    }
    @Override
    public Genre getOne(int ArtworkID){
        int genreID=artworkGenresDao.getOne(ArtworkID).getGenreId();
        Genre genre=genreDao.getOne(genreID);
        return genre;
    }
}
