package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MediaItems", schema = "dbo", catalog = "moshayof")
public class MediaItemsEntity {
    private long mid;
    private Long prodYear;

    @Id
    @Column(name = "MID", nullable = false, precision = 0)
    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "PROD_YEAR", nullable = true)
    public Long getProdYear() {
        return prodYear;
    }

    public void setProdYear(Long prodYear) {
        this.prodYear = prodYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaItemsEntity that = (MediaItemsEntity) o;
        return mid == that.mid && Objects.equals(prodYear, that.prodYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, prodYear);
    }

    @Override
    public String toString() {
        return "MediaItemsEntity{" +
                "mid=" + mid +
                ", prodYear=" + prodYear +
                '}';
    }
}
