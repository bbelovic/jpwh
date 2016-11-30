package org.bbelovic.jpwh.ch13;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.FetchMode.SELECT;

@Entity
@Table(name = "items")
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = "items_id_seq", strategy = SEQUENCE)
    @SequenceGenerator(name = "items_id_seq", sequenceName = "items_id_seq", schema = "public", allocationSize = 1)
    private Long id;
    private String name;
    @Version
    private Long version;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    @Fetch(SELECT)
    private Set<Bid> bids = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "images")
    @Column(name = "filename")
    @Fetch(SELECT)
    private Set<String> images = new HashSet<>();

    public Item() {
    }

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

    public Long getVersion() {
        return version;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }
}
