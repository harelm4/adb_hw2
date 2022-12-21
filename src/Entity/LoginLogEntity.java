package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "LoginLog", schema = "dbo", catalog = "moshayof")
public class LoginLogEntity {
    private long userid;
    private Date logintime;

    @Id
    @Column(name = "USERID", nullable = false, precision = 0)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "LOGINTIME", nullable = true)
    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginLogEntity that = (LoginLogEntity) o;
        return userid == that.userid && Objects.equals(logintime, that.logintime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, logintime);
    }

    @Override
    public String toString() {
        return "LoginLogEntity{" +
                "userid=" + userid +
                ", logintime=" + logintime +
                '}';
    }
}
