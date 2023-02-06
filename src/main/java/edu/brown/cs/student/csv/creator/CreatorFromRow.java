package edu.brown.cs.student.csv.creator;

import java.util.List;

public interface CreatorFromRow<T> {
  T create(List<String> row)
      throws FactoryFailureException, edu.brown.cs.student.csv.creator.FactoryFailureException;
}
