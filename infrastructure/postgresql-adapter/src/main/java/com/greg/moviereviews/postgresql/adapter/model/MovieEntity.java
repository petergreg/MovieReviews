package com.greg.moviereviews.postgresql.adapter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class MovieEntity {

  @Id
  @Column(name = "movie_id", nullable = false)
  private Long id;

  private String title;

  //  @OneToMany
  private String review;
}
