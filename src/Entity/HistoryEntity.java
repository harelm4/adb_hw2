package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "History", schema = "dbo", catalog = "moshayof")
@IdClass(HistoryEntityPK.class)
public class HistoryEntity {
    private long userid;
    private long mid;
    private Date viewtime;

    @Id
    @Column(name = "USERID", nullable = false, precision = 0)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "MID", nullable = false, precision = 0)
    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    @Id
    @Column(name = "VIEWTIME", nullable = false)
    public Date getViewtime() {
        return viewtime;
    }

    public void setViewtime(Date viewtime) {
        this.viewtime = viewtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryEntity that = (HistoryEntity) o;
        return userid == that.userid && mid == that.mid && Objects.equals(viewtime, that.viewtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, mid, viewtime);
    }

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "userid=" + userid +
                ", mid=" + mid +
                ", viewtime=" + viewtime +
                '}';
    }
}
