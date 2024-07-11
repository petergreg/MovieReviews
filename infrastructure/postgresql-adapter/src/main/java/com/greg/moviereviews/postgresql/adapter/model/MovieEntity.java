package com.greg.moviereviews.postgresql.adapter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class MovieEntity {

  @Id
  @GeneratedValue(generator="my_seq")
  @SequenceGenerator(name="my_seq",sequenceName="movie_seq", allocationSize=1)
  @Column(name = "movie_id")
  private Long id;

  private String title;

  //  @OneToMany
  private String review;
}
