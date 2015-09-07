package com.laranevans.springrestreact.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laranevans.springrestreact.jpa.LocalDateTimePersistenceConverter;
import com.laranevans.springrestreact.json.JsonDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

@MappedSuperclass
public abstract class TimestampedVersionedEntity extends VersionedEntity {

    @Column(name = "created_at")
    // Special converter needed to properly handle LocalDateTime in JPA/Repository.
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    // Special converter needed to properly handle LocalDateTime in JPA/Repository.
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime updatedAt;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void setCreationDate() {
        this.createdAt = LocalDateTime.now(TimeZone.getTimeZone("UTC").toZoneId());
    }

    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = LocalDateTime.now(TimeZone.getTimeZone("UTC").toZoneId());
    }

}