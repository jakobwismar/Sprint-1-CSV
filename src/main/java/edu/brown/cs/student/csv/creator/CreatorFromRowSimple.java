package edu.brown.cs.student.csv.creator;

import java.util.List;

public class CreatorFromRowSimple implements CreatorFromRow<List<String>> {
  public List<String> create(List<String> row) {
    return row;
  }
}
