package com.quick.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Device.
 */
@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tensanpham")
    private String tensanpham;

    @Column(name = "kichthuocmatthung")
    private String kichthuocmatthung;

    @Column(name = "kichthuocthanthung")
    private String kichthuocthanthung;

    @Column(name = "phukien")
    private String phukien;

    @Column(name = "chatlieu")
    private String chatlieu;

    @Column(name = "baohanh")
    private String baohanh;

    @Column(name = "diachi")
    private String diachi;

    @Size(max = 200000)
    @Column(name = "hotline", length = 200000)
    private String hotline;

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

    public Device tensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
        return this;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getKichthuocmatthung() {
        return kichthuocmatthung;
    }

    public Device kichthuocmatthung(String kichthuocmatthung) {
        this.kichthuocmatthung = kichthuocmatthung;
        return this;
    }

    public void setKichthuocmatthung(String kichthuocmatthung) {
        this.kichthuocmatthung = kichthuocmatthung;
    }

    public String getKichthuocthanthung() {
        return kichthuocthanthung;
    }

    public Device kichthuocthanthung(String kichthuocthanthung) {
        this.kichthuocthanthung = kichthuocthanthung;
        return this;
    }

    public void setKichthuocthanthung(String kichthuocthanthung) {
        this.kichthuocthanthung = kichthuocthanthung;
    }

    public String getPhukien() {
        return phukien;
    }

    public Device phukien(String phukien) {
        this.phukien = phukien;
        return this;
    }

    public void setPhukien(String phukien) {
        this.phukien = phukien;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public Device chatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
        return this;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getBaohanh() {
        return baohanh;
    }

    public Device baohanh(String baohanh) {
        this.baohanh = baohanh;
        return this;
    }

    public void setBaohanh(String baohanh) {
        this.baohanh = baohanh;
    }

    public String getDiachi() {
        return diachi;
    }

    public Device diachi(String diachi) {
        this.diachi = diachi;
        return this;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHotline() {
        return hotline;
    }

    public Device hotline(String hotline) {
        this.hotline = hotline;
        return this;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        return id != null && id.equals(((Device) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Device{" +
            "id=" + getId() +
            ", tensanpham='" + getTensanpham() + "'" +
            ", kichthuocmatthung='" + getKichthuocmatthung() + "'" +
            ", kichthuocthanthung='" + getKichthuocthanthung() + "'" +
            ", phukien='" + getPhukien() + "'" +
            ", chatlieu='" + getChatlieu() + "'" +
            ", baohanh='" + getBaohanh() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", hotline='" + getHotline() + "'" +
            "}";
    }
}
