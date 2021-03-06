package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Review {
    private int reviewId;
    private Integer artWorkId;
    private Integer customerId;
    private Timestamp reviewDate;
    private Integer rating;
    private String comment;
    @OneToOne
    private Review review;
    public Review(){}
    public Review(int reviewId){this.reviewId=reviewId;}
    public Review(int reviewId,int artWorkId,int customerId,Timestamp reviewDate,int rating,String comment){
        this.reviewId=reviewId;
        this.artWorkId=artWorkId;
        this.customerId=customerId;
        this.reviewId=reviewId;
        this.rating=rating;
        this.comment=comment;
    }
    @Id
    @Column(name = "ReviewId")
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Basic
    @Column(name = "ArtWorkId")
    public Integer getArtWorkId() {
        return artWorkId;
    }

    public void setArtWorkId(Integer artWorkId) {
        this.artWorkId = artWorkId;
    }

    @Basic
    @Column(name = "CustomerId")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "ReviewDate")
    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Basic
    @Column(name = "Rating")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "Comment")
    public String getComment() {
        if (comment!=null)
        return comment;
        return "No message about comment";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (reviewId != review.reviewId) return false;
        if (artWorkId != null ? !artWorkId.equals(review.artWorkId) : review.artWorkId != null) return false;
        if (customerId != null ? !customerId.equals(review.customerId) : review.customerId != null) return false;
        if (reviewDate != null ? !reviewDate.equals(review.reviewDate) : review.reviewDate != null) return false;
        if (rating != null ? !rating.equals(review.rating) : review.rating != null) return false;
        if (comment != null ? !comment.equals(review.comment) : review.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + (artWorkId != null ? artWorkId.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
