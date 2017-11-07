package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Gallery {
    private int galleryId;
    private String galleryName;
    private String galleryNativeName;
    private String galleryCity;
    private String galleryCountry;
    private Double latitude;
    private Double longitude;
    private String galleryWebSite;
    @OneToOne
    private Gallery gallery;
    public Gallery(){}
    public Gallery(int galleryID){this.galleryId=galleryID;}
    public Gallery(int galleryID, String galleryName,String galleryNativeName, String galleryCity, String galleryCountry, double latitude, double longitude, String galleryWebSite) {
    this.galleryId=galleryID;
    this.galleryName=galleryName;
    this.galleryNativeName=galleryNativeName;
    this.galleryCity=galleryCity;
    this.galleryCountry=galleryCountry;
    this.latitude=latitude;
    this.longitude=longitude;
    this.galleryWebSite=galleryWebSite;
    }

    @Id
    @Column(name = "GalleryID")
    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    @Basic
    @Column(name = "GalleryName")
    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    @Basic
    @Column(name = "GalleryNativeName")
    public String getGalleryNativeName() {
        if (galleryNativeName!=null)
        return galleryNativeName;
        return "No message about galleryNativeName..";
    }

    public void setGalleryNativeName(String galleryNativeName) {
        this.galleryNativeName = galleryNativeName;
    }

    @Basic
    @Column(name = "GalleryCity")
    public String getGalleryCity() {
        return galleryCity;
    }

    public void setGalleryCity(String galleryCity) {
        this.galleryCity = galleryCity;
    }

    @Basic
    @Column(name = "GalleryCountry")
    public String getGalleryCountry() {
        return galleryCountry;
    }

    public void setGalleryCountry(String galleryCountry) {
        this.galleryCountry = galleryCountry;
    }

    @Basic
    @Column(name = "Latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "Longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "GalleryWebSite")
    public String getGalleryWebSite() {
        return galleryWebSite;
    }

    public void setGalleryWebSite(String galleryWebSite) {
        this.galleryWebSite = galleryWebSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gallery gallery = (Gallery) o;

        if (galleryId != gallery.galleryId) return false;
        if (galleryName != null ? !galleryName.equals(gallery.galleryName) : gallery.galleryName != null) return false;
        if (galleryNativeName != null ? !galleryNativeName.equals(gallery.galleryNativeName) : gallery.galleryNativeName != null)
            return false;
        if (galleryCity != null ? !galleryCity.equals(gallery.galleryCity) : gallery.galleryCity != null) return false;
        if (galleryCountry != null ? !galleryCountry.equals(gallery.galleryCountry) : gallery.galleryCountry != null)
            return false;
        if (latitude != null ? !latitude.equals(gallery.latitude) : gallery.latitude != null) return false;
        if (longitude != null ? !longitude.equals(gallery.longitude) : gallery.longitude != null) return false;
        if (galleryWebSite != null ? !galleryWebSite.equals(gallery.galleryWebSite) : gallery.galleryWebSite != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = galleryId;
        result = 31 * result + (galleryName != null ? galleryName.hashCode() : 0);
        result = 31 * result + (galleryNativeName != null ? galleryNativeName.hashCode() : 0);
        result = 31 * result + (galleryCity != null ? galleryCity.hashCode() : 0);
        result = 31 * result + (galleryCountry != null ? galleryCountry.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (galleryWebSite != null ? galleryWebSite.hashCode() : 0);
        return result;
    }
}
