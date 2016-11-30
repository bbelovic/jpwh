package org.bbelovic.jpwh;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static java.lang.String.format;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "forest")
public class Forest implements Serializable {
    private static final String SEQ_NAME = "forest_id_seq";
    @Id
    @GeneratedValue(generator = SEQ_NAME, strategy = SEQUENCE)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Forest)) return false;
        Forest forest = (Forest) o;
        return Objects.equals(getId(), forest.getId()) &&
                Objects.equals(getName(), forest.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return format("Forest[id=%d, name='%s']", id, name);
    }
}
