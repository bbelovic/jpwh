package org.bbelovic.jpwh;

import org.bbelovic.jpwh.ch14.LoggingListener;
import org.hibernate.annotations.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.LazyCollectionOption.EXTRA;

@Entity
@Table(name = "tree")
@BatchSize(size = 3)
@NamedEntityGraphs(
        @NamedEntityGraph(
                attributeNodes = @NamedAttributeNode(value = "branches"))
)
@EntityListeners({LoggingListener.class})
@Audited
public class Tree implements Serializable {
    private static final String TREE_ID_SEQ = "tree_id_seq";

    private Long id;
    private String name;
    private int conifer;
    private Forest forest;
    private LumberJack lumberJack;

    private Set<Branch> branches = new HashSet<>();


    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = TREE_ID_SEQ)
    @SequenceGenerator(name = TREE_ID_SEQ, sequenceName = TREE_ID_SEQ, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    @ManyToOne
    public Forest getForest() {
        return forest;
    }

    public void setForest(Forest forest) {
        this.forest = forest;
    }

    @ManyToOne
    public LumberJack getLumberJack() {
        return lumberJack;
    }

    public void setLumberJack(LumberJack lumberJack) {
        this.lumberJack = lumberJack;
    }

    @LazyCollection(EXTRA)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "tree", cascade = {CascadeType.ALL})
    public Set<Branch> getBranches() {
        return branches;
    }

    public int getConifer() {
        return conifer;
    }

    public void setConifer(int conifer) {
        this.conifer = conifer;
    }

    @PostLoad
    public void postLoadOnEntityClass() {
        System.out.println("@@@ post load on entity");
    }

    @Override
    public String toString() {
        return format("Tree[id=%d, name='%s']", id, name);
    }
}
