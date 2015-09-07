package com.laranevans.springrestreact.repository;

import com.laranevans.springrestreact.model.domain.Possession;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(collectionResourceRel = "possessions", path="possessions")
public interface PossessionRepository extends PagingAndSortingRepository<Possession, Long> {}
