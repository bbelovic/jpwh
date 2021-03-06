package org.bbelovic.jpwh;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static java.lang.String.format;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "lumberjack")
public class LumberJack implements Serializable {
    private static final String SEQ_NAME = "forest_id_seq";
    @Id
    @GeneratedValue(generator = SEQ_NAME, strategy = SEQUENCE)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return format("LumberJack[id=%d, name='%s']", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LumberJack)) return false;
        LumberJack that = (LumberJack) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
