package com.greg.moviereviews.postgresql.adapter.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "movie")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovieEntity {

  @Id @GeneratedValue @Include private UUID id;

  @Include private String title;

  @Include private String author;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  @Default
  private List<ReviewEntity> reviews = new ArrayList<>();

  public void addReviewEntity(final ReviewEntity reviewEntity) {
    reviews.add(reviewEntity);
    reviewEntity.setMovie(this);
  }
}
