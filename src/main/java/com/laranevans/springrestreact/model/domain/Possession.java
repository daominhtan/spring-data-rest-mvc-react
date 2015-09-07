package com.laranevans.springrestreact.model.domain;

import com.laranevans.springrestreact.model.TimestampedVersionedEntity;

import javax.persistence.*;

@Entity
@Table(name = "children")
public class Possession extends TimestampedVersionedEntity {

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
