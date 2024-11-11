package com.greg.moviereviews.rest.model;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiMovie {

  UUID id;
  String title;
  String author;
  List<ApiReview> reviews;
}
