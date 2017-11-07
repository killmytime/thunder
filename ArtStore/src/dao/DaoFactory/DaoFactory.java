package dao.DaoFactory;


import dao.*;
import dao.impl.*;
import entity.*;

public class DaoFactory {
    public static ArtistDao getArtistDaoInstance() {
        return new ArtistDaoImpl();
    }

    public static ArtworkDao getArtworkDaoInstance() {
        return new ArtworkDaoImpl();
    }

    public static ArtworkGenresDao getArtworkGenresDaoInstance() {
        return new ArtworkGenresDaoImpl();
    }

    public static ArtworkSubjectsDao getArtworkSubjectsDaoInstance(){return new ArtworkSubjectsDaoImpl();}

    public static CartDao getCartDaoInstance(){return new CartDaoImpl();}

    public static CustomerDao getCustomerDaoInstance() { return new CustomerDaoImpl(); }

    public static CustomerLogonDao getCustomerLogonDaoInstance() { return new CustomerLogonDaoImpl(); }

//    public static GalleryDao getGalleryDaoInstance(){return new GalleryDaoImpl();}

    public static GenreDao getGenreDaoInstance() {
        return new GenreDaoImpl();
    }

    public static OrderDao getOrderDaoInstance(){return new OrderDaoImpl();}

//    public static ReviewDao getReviewDaoInstance(){return new ReviewDaoImpl();}

    public static SubjectDao getSubjectDaoInstance(){return new SubjectDaoImpl();}

    public static TypesframesDao getTypesframesDaoInstance(){return new TypesframesDaoImpl();}

    public static TypesglassDao getTypesglassDaoINstance(){return new TypesglassDaoImpl();}

    public static TypesmattDao getTypesmattDaoInstance(){return new TypesmattDaoImpl();}

    public static TypesshippersDao getTypesshipersDaoInstance(){return new TypesshippersDaoImpl();}

//    public static TypesstatuscodesDao getTypesstatuscodesDaoInstance(){return new TypesstatuscodesDaoImpl();}
}
