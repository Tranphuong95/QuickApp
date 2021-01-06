package com.quick.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Productest.
 */
@Entity
@Table(name = "productest")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Productest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tensanpham")
    private String tensanpham;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public Productest tensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
        return this;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Productest)) {
            return false;
        }
        return id != null && id.equals(((Productest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Productest{" +
            "id=" + getId() +
            ", tensanpham='" + getTensanpham() + "'" +
            "}";
    }
}
