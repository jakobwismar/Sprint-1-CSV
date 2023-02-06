package edu.brown.cs.student.csv.creator;

import java.util.List;

public class CreateStudentFromRow implements CreatorFromRow<Student> {

  public Student create(List<String> row) throws FactoryFailureException {

    try {
      return new Student(row);

    } catch (Exception e) {
      throw new FactoryFailureException(e.getMessage(), row);
    }
  }
}
