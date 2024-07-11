package com.greg.moviereviews.postgresql.adapter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "review")
public class ReviewEntity {

  @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id", nullable = false)
  private Long id;

  private String author;
  private String reviewBody;
}
