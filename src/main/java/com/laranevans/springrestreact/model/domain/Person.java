package com.laranevans.springrestreact.model.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laranevans.springrestreact.jpa.LocalDatePersistenceConverter;
import com.laranevans.springrestreact.model.TimestampedVersionedEntity;
import com.laranevans.springrestreact.json.JsonDateSerializer;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * I created a simple Person/Possession structure here just to illustrate sub-resources and the relative linking/hateos
 * parts of Spring re: REST.
 *
 * A Person is not intended to be the same thing as a User for authentication purposes. The fact that this class is
 * called Person isn't meant to indicate any connotation of a security Principal or entity that can be authenticated.
 *
 * Users and People are distinct.
 */
@Entity
@Table(name = "people")
public class Person extends TimestampedVersionedEntity {

    // FIXME: This needs to tie to the UserDetails in spring-security
    private Integer userId;

    // Just to demonstrate how to handle LocalDate
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dateOfBirth;

    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Possession> possessions;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Just to show how to serialize a LocalDate into JSON.
    @JsonSerialize(using = JsonDateSerializer.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Possession> getPossessions() {
        return possessions;
    }

    public void setPossessions(List<Possession> possessions) {
        this.possessions = possessions;
    }

}
