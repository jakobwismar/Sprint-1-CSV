package edu.brown.cs.student.csv.creator;

import java.util.List;

public class CreatorJoinColumns implements CreatorFromRow<String> {

  public String create(List<String> row) {
    String result = "";
    for (String column : row) {
      result += column + "\t";
    }
    return result;
  }
}
