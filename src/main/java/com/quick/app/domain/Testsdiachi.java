package com.quick.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Testsdiachi.
 */
@Entity
@Table(name = "testsdiachi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Testsdiachi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tinh")
    private String tinh;

    @Column(name = "matinh")
    private Long matinh;

    @Column(name = "quanhuyen")
    private String quanhuyen;

    @Column(name = "maquanhuyen")
    private Long maquanhuyen;

    @Column(name = "phuongxa")
    private String phuongxa;

    @Column(name = "maphuongxa")
    private Long maphuongxa;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTinh() {
        return tinh;
    }

    public Testsdiachi tinh(String tinh) {
        this.tinh = tinh;
        return this;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public Long getMatinh() {
        return matinh;
    }

    public Testsdiachi matinh(Long matinh) {
        this.matinh = matinh;
        return this;
    }

    public void setMatinh(Long matinh) {
        this.matinh = matinh;
    }

    public String getQuanhuyen() {
        return quanhuyen;
    }

    public Testsdiachi quanhuyen(String quanhuyen) {
        this.quanhuyen = quanhuyen;
        return this;
    }

    public void setQuanhuyen(String quanhuyen) {
        this.quanhuyen = quanhuyen;
    }

    public Long getMaquanhuyen() {
        return maquanhuyen;
    }

    public Testsdiachi maquanhuyen(Long maquanhuyen) {
        this.maquanhuyen = maquanhuyen;
        return this;
    }

    public void setMaquanhuyen(Long maquanhuyen) {
        this.maquanhuyen = maquanhuyen;
    }

    public String getPhuongxa() {
        return phuongxa;
    }

    public Testsdiachi phuongxa(String phuongxa) {
        this.phuongxa = phuongxa;
        return this;
    }

    public void setPhuongxa(String phuongxa) {
        this.phuongxa = phuongxa;
    }

    public Long getMaphuongxa() {
        return maphuongxa;
    }

    public Testsdiachi maphuongxa(Long maphuongxa) {
        this.maphuongxa = maphuongxa;
        return this;
    }

    public void setMaphuongxa(Long maphuongxa) {
        this.maphuongxa = maphuongxa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Testsdiachi)) {
            return false;
        }
        return id != null && id.equals(((Testsdiachi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Testsdiachi{" +
            "id=" + getId() +
            ", tinh='" + getTinh() + "'" +
            ", matinh=" + getMatinh() +
            ", quanhuyen='" + getQuanhuyen() + "'" +
            ", maquanhuyen=" + getMaquanhuyen() +
            ", phuongxa='" + getPhuongxa() + "'" +
            ", maphuongxa=" + getMaphuongxa() +
            "}";
    }
}
