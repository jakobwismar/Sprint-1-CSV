package edu.brown.cs.student.csv;

import java.util.ArrayList;
import java.util.List;

/** Class for Searcher object */
public class Searcher {

  private final List<List<String>> results;

  /**
   * Constructor for Searcher object
   *
   * @param results the records/rows to search through
   */
  public Searcher(List<List<String>> results) {

    if (results == null) {
      throw new IllegalArgumentException("Results parameter is empty.");
    }

    this.results = results;
  }

  /**
   * Search all columns for a string
   *
   * @param searchText: the string to search for
   * @return the list of rows containing the desired string
   */
  public List<List<String>> findString(String searchText) {
    return findString(searchText, -1);
  }

  /**
   * Search a column specified by name for a string, assuming the CSV has a header with unique names
   *
   * @param searchText: the string to search for
   * @param columnName: name of the column to search in
   * @return the list of rows containing searchText, or null if no results are found
   */
  public List<List<String>> findString(String searchText, String columnName) {

    if (this.results.size() < 1) return null;

    // find the index of the column with columnName
    Integer columnIndex;
    List<String> headerRow = this.results.get(0);
    for (columnIndex = 0; columnIndex < headerRow.size(); columnIndex++) {
      if (headerRow.get(columnIndex).equals(columnName)) {
        break;
      }
    }

    // if a column is found, search
    if (columnIndex < headerRow.size()) {
      return findString(searchText, columnIndex);
    }
    return null;
  }

  /**
   * Search a column specified by index for a string
   *
   * @param searchText: the string to search for
   * @return the list of rows containing searchText, or null if no results are found
   */
  public List<List<String>> findString(String searchText, Integer columnIndex) {

    List<List<String>> searchResults = new ArrayList<>();

    for (List<String> row : this.results) {

      if (columnIndex > row.size() - 1) {
        break;
      } else if ((columnIndex >= 0) && row.get(columnIndex).contains(searchText)) {
        searchResults.add(row);
      } else if (columnIndex < 0) {
        for (String column : row) {
          if (column.contains(searchText)) {
            searchResults.add(row);
          }
        }
      }
    }

    return searchResults;
  }
}
