package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Artist {
    private int artistId;
    private String firstName;
    private String lastName;
    private String nationality;
    private Integer yearOfBirth;
    private Integer yearOfDeath;
    private String details;
    private String artistLink;
    @OneToOne
    private Artist artistk;
    public Artist(){}
    public Artist(int artistId){this.artistId=artistId;}
    public Artist(int artistID, String firstName, String lastName, String nationality, int yearOfBirth, int yearOfDeath, String details, String artistLink) {
        this.artistId=artistID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.nationality=nationality;
        this.yearOfBirth=yearOfBirth;
        this.yearOfDeath=yearOfDeath;
        this.details=details;
        this.artistLink=artistLink;
    }

    @Id
    @Column(name = "ArtistID")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "Nationality")
    public String getNationality() {
        if (nationality!=null)
        return nationality;
        return "No message about nationality.";
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "YearOfBirth")
    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Basic
    @Column(name = "YearOfDeath")
    public Integer getYearOfDeath() {
        return yearOfDeath;
    }

    public void setYearOfDeath(Integer yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }

    @Basic
    @Column(name = "Details")
    public String getDetails() {
        if (details!=null)
        return details;
        return "No message about details.";
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "ArtistLink")
    public String getArtistLink() {
        if (artistLink!=null)
        return artistLink;
        return "No message about artistLink.";
    }

    public void setArtistLink(String artistLink) {
        this.artistLink = artistLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (artistId != artist.artistId) return false;
        if (firstName != null ? !firstName.equals(artist.firstName) : artist.firstName != null) return false;
        if (lastName != null ? !lastName.equals(artist.lastName) : artist.lastName != null) return false;
        if (nationality != null ? !nationality.equals(artist.nationality) : artist.nationality != null) return false;
        if (yearOfBirth != null ? !yearOfBirth.equals(artist.yearOfBirth) : artist.yearOfBirth != null) return false;
        if (yearOfDeath != null ? !yearOfDeath.equals(artist.yearOfDeath) : artist.yearOfDeath != null) return false;
        if (details != null ? !details.equals(artist.details) : artist.details != null) return false;
        if (artistLink != null ? !artistLink.equals(artist.artistLink) : artist.artistLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artistId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (yearOfBirth != null ? yearOfBirth.hashCode() : 0);
        result = 31 * result + (yearOfDeath != null ? yearOfDeath.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (artistLink != null ? artistLink.hashCode() : 0);
        return result;
    }
}
