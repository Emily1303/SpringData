package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    private GP gp;

    public Visitation() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public GP getGp() {
        return gp;
    }

    public void setGp(GP gp) {
        this.gp = gp;
    }

}
