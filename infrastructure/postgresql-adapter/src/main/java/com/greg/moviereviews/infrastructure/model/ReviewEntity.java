package com.greg.moviereviews.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

  @Id private String id;

  private String author;

  private String review;
}
