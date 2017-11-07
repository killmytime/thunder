package service.impl;

import dao.ArtistDao;
import dao.ArtworkDao;
import dao.DaoFactory.DaoFactory;
import entity.Artist;
import service.ArtistService;
import util.PageBean;

import java.util.ArrayList;
import java.util.List;

public class ArtistServiceImpl implements ArtistService {
    private ArtistDao artistDao;
    private ArtworkDao artworkDao;
    public ArtistServiceImpl(){
        artistDao= DaoFactory.getArtistDaoInstance();
        artworkDao=DaoFactory.getArtworkDaoInstance();
    }
    @Override
    public List<Artist> getAll(){
        List<Artist> artists= artistDao.getAll();
        return artists;
    }
    @Override
    public Artist getByArtwork(int ArtworkID){
        int artistID=artworkDao.getOne(ArtworkID).getArtistId();
        Artist artist=artistDao.getOne(artistID);
        return artist;
    }
    @Override
    public void getAll(PageBean<Artist> pb) {

        // 查询总记录数;  设置到pb对象中
        int totalCount = artistDao.getAll().size();
        pb.setTotalCount(totalCount);

        // 判断
        if (pb.getCurrentPage() <=0) {
            pb.setCurrentPage(1);                        // 把当前页设置为1
        } else if (pb.getCurrentPage() > pb.getTotalPage()){
            pb.setCurrentPage(pb.getTotalPage());        // 把当前页设置为最大页数
        }

        //1. 获取当前页： 计算查询的起始行、返回的行数
        int currentPage = pb.getCurrentPage();
        int count = pb.getPageCount();                            // 查询返回的行数
        int index = (currentPage -1 ) * pb.getPageCount();        // 查询的起始行
        List<Artist> artistsCurrent=new ArrayList<>();
        for (int i=0;i<count;i++){
            Artist artist=artistDao.getAll().get(index+i);
            if (artist!=null){
                artistsCurrent.add(artist);
            }
        }
        pb.setPageData(artistsCurrent);
    }
}
