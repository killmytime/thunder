package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Artworksubjects {
    private int artWorkSubjectId;
    private Integer artWorkId;
    private Integer subjectId;
@OneToOne
private Artworksubjects artworksubjects;
public Artworksubjects(){}
public Artworksubjects(int artWorkSubjectId){this.artWorkId=artWorkSubjectId;}
    public Artworksubjects(int artWorkSubjectID, int artWorkID, int subjectID) {
    this.artWorkSubjectId=artWorkSubjectID;
    this.artWorkId=artWorkID;
    this.subjectId=subjectID;
    }

    @Id
    @Column(name = "ArtWorkSubjectID")
    public int getArtWorkSubjectId() {
        return artWorkSubjectId;
    }

    public void setArtWorkSubjectId(int artWorkSubjectId) {
        this.artWorkSubjectId = artWorkSubjectId;
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
    @Column(name = "SubjectID")
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artworksubjects that = (Artworksubjects) o;

        if (artWorkSubjectId != that.artWorkSubjectId) return false;
        if (artWorkId != null ? !artWorkId.equals(that.artWorkId) : that.artWorkId != null) return false;
        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artWorkSubjectId;
        result = 31 * result + (artWorkId != null ? artWorkId.hashCode() : 0);
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }
}
