package org.bbelovic.jpwh.ch13;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "bid")
public class Bid implements Serializable {
    private static final String BID_ID_SEQ = "bid_id_seq";
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = BID_ID_SEQ)
    @SequenceGenerator(name = BID_ID_SEQ, sequenceName = BID_ID_SEQ, allocationSize = 1, schema = "public")
    private Long id;
    @ManyToOne(fetch = LAZY)
    private Item item;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
