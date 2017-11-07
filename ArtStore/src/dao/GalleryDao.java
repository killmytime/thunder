package dao;
import entity.Gallery;

import java.util.List;

public interface GalleryDao {
    List<Gallery> getAll();
    Gallery getOne(int GalleryID);
}
