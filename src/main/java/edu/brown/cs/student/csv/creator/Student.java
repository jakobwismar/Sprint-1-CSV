package edu.brown.cs.student.csv.creator;

import java.util.List;

public class Student {
  private String name;
  private String starSign;
  private int grade;

  public Student(List<String> row) throws Exception {
    this.name = row.get(0);
    this.starSign = row.get(1);
    this.grade = Integer.parseInt(row.get(2));
  }

  @Override
  public String toString() {

    String grade = String.valueOf(this.grade);

    return ("Name:" + name + "\nStar Sign: " + starSign + "\nGrade: " + grade + "\n");
  }
}
