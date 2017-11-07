package test;

import dao.DaoFactory.DaoFactory;
import entity.Artwork;

public class getTest {
    public static void main(String[] args) {
        Artwork artwork= DaoFactory.getArtworkDaoInstance().getOne(1);
        System.out.println(artwork);
    }
}
