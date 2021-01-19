package com.quick.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A Serveranh.
 */
@Entity
@Table(name = "serveranh")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Serveranh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] image;

//    @Column(name = "image_content_type")
//    private String imageContentType;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Type(type = "uuid-char")
    @Column(name = "uuid", length = 36)
    private UUID uuid=UUID.randomUUID();

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

    public Serveranh image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Serveranh imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Serveranh uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Serveranh)) {
            return false;
        }
        return id != null && id.equals(((Serveranh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Serveranh{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", uuid='" + getUuid() + "'" +
            "}";
    }
}
