package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Typesmatt {
    private int mattId;
    private String title;
    private String colorCode;
@OneToOne
private Typesmatt typesmatt;
public Typesmatt(){}
public Typesmatt(int mattId){this.mattId=mattId;}
public Typesmatt(int mattId,String title,String colorCode){
    this.mattId=mattId;
    this.title=title;
    this.colorCode=colorCode;
}
    @Id
    @Column(name = "MattID")
    public int getMattId() {
        return mattId;
    }

    public void setMattId(int mattId) {
        this.mattId = mattId;
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
    @Column(name = "ColorCode")
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Typesmatt typesmatt = (Typesmatt) o;

        if (mattId != typesmatt.mattId) return false;
        if (title != null ? !title.equals(typesmatt.title) : typesmatt.title != null) return false;
        if (colorCode != null ? !colorCode.equals(typesmatt.colorCode) : typesmatt.colorCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mattId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (colorCode != null ? colorCode.hashCode() : 0);
        return result;
    }
}
