package edu.brown.cs.student.csv;

import edu.brown.cs.student.csv.creator.CreatorFromRow;
import edu.brown.cs.student.csv.creator.FactoryFailureException;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Class for Parser object */
public class Parser<T> {

  private Reader reader;
  private CreatorFromRow<T> creator;

  private boolean containsHeader = false;
  private String separator = ",";

  private String[] columnNames = null;
  private int columnCount = 0;

  private List<List<String>> rows = null;
  private List<T> rowsProcessed = null;
  /**
   * Constructor for Parser object
   *
   * @param reader: Reader data
   * @param creatorFromRow: A formatter for the row
   */
  public Parser(Reader reader, CreatorFromRow<T> creatorFromRow) {

    if (creatorFromRow == null) {
      throw new IllegalArgumentException("Creator strategy cannot be null.");
    } else if (reader == null) {
      throw new IllegalArgumentException("Reader cannot be null.");
    }

    this.reader = reader;
    this.creator = creatorFromRow;
  }

  /**
   * Constructor for Parser object
   *
   * @param reader: Reader data
   * @param containsHeader: Indicates whether the data to be parsed has a header line
   * @param separator: String marking boundary for elements in each row
   * @param creatorFromRow: A row formatter
   */
  public Parser(
      Reader reader, CreatorFromRow<T> creatorFromRow, Boolean containsHeader, String separator) {

    if (creatorFromRow == null) {
      throw new IllegalArgumentException("Formatter is null");
    } else if (reader == null) {
      throw new IllegalArgumentException("Reader is null");
    }

    this.reader = reader;
    this.creator = creatorFromRow;
    this.containsHeader = containsHeader;
    this.separator = separator;
  }

  /**
   * Parse with default arguments
   *
   * @throws Exception
   */
  public List<T> Parse() throws Exception {
    return Parse(this.containsHeader, this.separator);
  }

  /**
   * Parse with specified parameters
   *
   * @param isHeaderPresent: Indicates whether the data to be parsed has a header line
   * @param separator: String marking boundary for elements in each row
   * @throws Exception
   */
  public List<T> Parse(Boolean isHeaderPresent, String separator)
      throws Exception, FactoryFailureException {

    this.columnNames = null;
    this.rows = new ArrayList<>();
    this.rowsProcessed = new ArrayList<T>();
    this.columnCount = 0;

    Boolean firstLine = true;

    BufferedReader bufReader = new BufferedReader(reader);

    String line = "";
    try {
      while ((line = bufReader.readLine()) != null) {
        if (line.length() > 0) {
          if (firstLine && isHeaderPresent) {
            this.columnCount = columnNames.length;
            rows.add(Arrays.asList(columnNames));
            columnNames = line.split(separator);
          } else {
            addRow(line, separator);
          }
          firstLine = false;
        }
      }
    } catch (FactoryFailureException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }
    return rowsProcessed;
  }

  /**
   * Splits the row/line read from the Reader into columns and gets them processed by the creator
   * object
   *
   * @param line: Row to be split
   * @param separator: String marking the boundary for the row elements
   */
  private void addRow(String line, String separator) throws FactoryFailureException {

    List<String> columnValues = Arrays.asList(line.split(separator));

    rows.add(columnValues);
    rowsProcessed.add(this.creator.create(columnValues));

    if (this.columnCount < columnValues.size()) {
      this.columnCount = columnValues.size();
    }
  }

  /**
   * Returns the records that were not processed by CreatorFromRow processor.
   *
   * @return List rows where each row is a list of columns.
   */
  public List<List<String>> getRecords() {
    return this.rows;
  }

  /**
   * Get number of rows
   *
   * @return Number of rows.
   */
  public Integer getRecordCount() {
    return this.rows.size() - (this.containsHeader ? 1 : 0);
  }

  /**
   * Get max number of columns
   *
   * @return Number of columns
   */
  public Integer getColumnCount() {
    return this.columnCount;
  }

  /**
   * Get column names from the CSV header
   *
   * @return String array of column names
   */
  public String[] getColumnNames() {
    return this.columnNames;
  }
}
