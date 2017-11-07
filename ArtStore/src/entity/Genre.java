package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Genre {
    private int genreId;
    private String genreName;
    private Integer era;
    private String description;
    private String link;

    @OneToOne
    private Genre genre;
    public Genre(){}
    public Genre(int genreID){this.genreId=genreID;}
    public Genre(int genreID, String genreName, int era, String description, String link) {
        this.genreId=genreID;
        this.genreName=genreName;
        this.era=era;
        this.description=description;
        this.link=link;
    }

    @Id
    @Column(name = "GenreID")
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Basic
    @Column(name = "GenreName")
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Basic
    @Column(name = "Era")
    public Integer getEra() {
        return era;
    }

    public void setEra(Integer era) {
        this.era = era;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        if (genreName != null ? !genreName.equals(genre.genreName) : genre.genreName != null) return false;
        if (era != null ? !era.equals(genre.era) : genre.era != null) return false;
        if (description != null ? !description.equals(genre.description) : genre.description != null) return false;
        if (link != null ? !link.equals(genre.link) : genre.link != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genreId;
        result = 31 * result + (genreName != null ? genreName.hashCode() : 0);
        result = 31 * result + (era != null ? era.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
