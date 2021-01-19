package com.quick.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImageServer.
 */
@Entity
@Table(name = "image_server")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImageServer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public ImageServer image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public ImageServer imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImageServer)) {
            return false;
        }
        return id != null && id.equals(((ImageServer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageServer{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}

//package com.quick.app.domain;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
//import java.io.Serializable;
//import java.util.UUID;
//
///**
// * A ImageServer.
// */
//@Entity
//@Table(name = "image_server")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//public class ImageServer implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//    private UUID id;
//
//    @Lob
//    @Column(name = "image")
//    private byte[] image;
//
//    @Column(name = "image_content_type")
//    private String imageContentType;
//
//    // jhipster-needle-entity-add-field - JHipster will add fields here
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public ImageServer image(byte[] image) {
//        this.image = image;
//        return this;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    public String getImageContentType() {
//        return imageContentType;
//    }
//
//    public ImageServer imageContentType(String imageContentType) {
//        this.imageContentType = imageContentType;
//        return this;
//    }
//
//    public void setImageContentType(String imageContentType) {
//        this.imageContentType = imageContentType;
//    }
//    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof ImageServer)) {
//            return false;
//        }
//        return id != null && id.equals(((ImageServer) o).id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//
//    // prettier-ignore
//    @Override
//    public String toString() {
//        return "ImageServer{" +
//          "id=" + getId() +
//          ", image='" + getImage() + "'" +
//          ", imageContentType='" + getImageContentType() + "'" +
//          "}";
//    }
//}