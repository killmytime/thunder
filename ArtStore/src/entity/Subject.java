package entity;

import javax.persistence.*;

/**
 * Created by leiwe on 2017/7/14.
 */
@Entity
public class Subject {
    private int subjectId;
    private String subjectName;
@OneToOne
private Subject subject;
    public Subject(){}
    public Subject(int subjectId){this.subjectId=subjectId;}
    public Subject(int subjectId, String subjectName) {
        this.subjectId=subjectId;
        this.subjectName=subjectName;
    }

    @Id
    @Column(name = "SubjectId")
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "SubjectName")
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (subjectId != subject.subjectId) return false;
        if (subjectName != null ? !subjectName.equals(subject.subjectName) : subject.subjectName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subjectId;
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        return result;
    }
}
