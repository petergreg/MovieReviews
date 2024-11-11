package com.greg.moviereviews.postgresql.adapter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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
  @GeneratedValue
  @Column(name = "movie_id")
  private UUID id;

  private String title;

  private String author;

  //  @OneToMany
  private String review;
}
