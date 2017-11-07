package entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Typesframes {
    private int frameId;
    private String title;
    private BigDecimal price;
    private String color;
    private String style;
@OneToOne
private Typesframes typesframes;
public Typesframes(){}
public Typesframes(int frameId){this.frameId=frameId;}
public Typesframes(int frameId,String title,BigDecimal price,String color,String style){
    this.frameId=frameId;
    this.title=title;
    this.price=price;
    this.color=color;
    this.style=style;
}
    @Id
    @Column(name = "FrameID")
    public int getFrameId() {
        return frameId;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "Style")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Typesframes that = (Typesframes) o;

        if (frameId != that.frameId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (style != null ? !style.equals(that.style) : that.style != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = frameId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        return result;
    }
}
