package service.impl;

import dao.ArtworkDao;
import dao.ArtworkSubjectsDao;
import dao.DaoFactory.DaoFactory;
import dao.SubjectDao;
import entity.Subject;
import service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDao subjectDao;
    private ArtworkSubjectsDao artworkSubjectsDao;
    public SubjectServiceImpl(){
        subjectDao= DaoFactory.getSubjectDaoInstance();
        artworkSubjectsDao=DaoFactory.getArtworkSubjectsDaoInstance();
    }
    @Override
    public Subject getByArtwork(int ArtworkID){
        int subjectID=artworkSubjectsDao.getOne(ArtworkID).getSubjectId();
        Subject subject=subjectDao.getOne(subjectID);
        return subject;
    }
}
