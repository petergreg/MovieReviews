package com.greg.moviereviews.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

  @Id private String id;
  private String title;

  @OneToMany private Set<ReviewEntity> reviews = new HashSet<>();
}
