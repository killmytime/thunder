package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Artworkgenres {
    private int artWorkGenreId;
    private Integer artWorkId;
    private Integer genreId;
@OneToOne
private Artworkgenres artworkgenres;
public Artworkgenres(){}
public Artworkgenres(int ArtWorkGenreID){this.artWorkGenreId=ArtWorkGenreID;}
    public Artworkgenres(int artWorkGenreID, int artWorkID, int genreID) {
    this.artWorkId=artWorkID;
    this.genreId=genreID;
    }

    @Id
    @Column(name = "ArtWorkGenreID")
    public int getArtWorkGenreId() {
        return artWorkGenreId;
    }

    public void setArtWorkGenreId(int artWorkGenreId) {
        this.artWorkGenreId = artWorkGenreId;
    }

    @Basic
    @Column(name = "ArtWorkID")
    public Integer getArtWorkId() {
        return artWorkId;
    }

    public void setArtWorkId(Integer artWorkId) {
        this.artWorkId = artWorkId;
    }

    @Basic
    @Column(name = "GenreID")
    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artworkgenres that = (Artworkgenres) o;

        if (artWorkGenreId != that.artWorkGenreId) return false;
        if (artWorkId != null ? !artWorkId.equals(that.artWorkId) : that.artWorkId != null) return false;
        if (genreId != null ? !genreId.equals(that.genreId) : that.genreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artWorkGenreId;
        result = 31 * result + (artWorkId != null ? artWorkId.hashCode() : 0);
        result = 31 * result + (genreId != null ? genreId.hashCode() : 0);
        return result;
    }
}
