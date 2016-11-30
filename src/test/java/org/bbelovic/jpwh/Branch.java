package org.bbelovic.jpwh;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "branch")
@Audited
@FilterDef(
        name = "coniferBranch",
        parameters = {@ParamDef( name = "conifer", type = "int")}

)
@Filter(
        name = "coniferBranch",
        condition = ":conifer = (select t.conifer from Tree t where t.id = tree_id)"

)
public class Branch implements Serializable {
    private static final String BRANCH_ID_SEQ = "branch_id_seq";

    private Long id;
    private String name;

    private Tree tree;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = BRANCH_ID_SEQ)
    @SequenceGenerator(name = BRANCH_ID_SEQ, sequenceName = BRANCH_ID_SEQ, allocationSize = 1)
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

    @ManyToOne(fetch = FetchType.LAZY)
    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
