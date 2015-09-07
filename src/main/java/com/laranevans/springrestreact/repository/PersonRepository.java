package com.laranevans.springrestreact.repository;

import com.laranevans.springrestreact.model.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "people", path="people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    // Added in this finder here just to show how to setup a finder for something a bit more complex, like a LocalDate.
    @RestResource
    Person findByDateOfBirth(@Param("dataOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth);

}
