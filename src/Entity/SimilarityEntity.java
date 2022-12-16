package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Similarity", schema = "dbo", catalog = "moshayof")
@IdClass(SimilarityEntityPK.class)
public class SimilarityEntity {
    private long mid1;
    private long mid2;
    private Float similarity;

    @Id
    @Column(name = "MID1", nullable = false)
    public long getMid1() {
        return mid1;
    }

    public void setMid1(long mid1) {
        this.mid1 = mid1;
    }

    @Id
    @Column(name = "MID2", nullable = false)
    public long getMid2() {
        return mid2;
    }

    public void setMid2(long mid2) {
        this.mid2 = mid2;
    }

    @Basic
    @Column(name = "SIMILARITY", nullable = true, precision = 0)
    public Float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Float similarity) {
        this.similarity = similarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimilarityEntity that = (SimilarityEntity) o;
        return mid1 == that.mid1 && mid2 == that.mid2 && Objects.equals(similarity, that.similarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid1, mid2, similarity);
    }
}
