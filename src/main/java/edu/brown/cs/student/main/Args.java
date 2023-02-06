package edu.brown.cs.student.main;

public class Args {
  private String filePath = null;
  private String searchText = null;
  private Integer searchColumnIndex = -1;
  private String searchColumnName = null;
  private Boolean hasHeader = false;

  /**
   * Generate an instance of the Args class based on the string array of command arguments.
   *
   * @param args: String[] of arguments
   */
  public Args(String[] args) {

    if (args.equals(null)) return;
    for (int i = 0; i < args.length; ) {
      i = processNextArgument(i, args);
    }
  }

  /**
   * Process the next argument and update argIndex
   *
   * @param argIndex: Index of the argument to be processed
   * @param args: string array of the arguments
   * @return updated value of argIndex for next call
   */
  private Integer processNextArgument(Integer argIndex, String[] args) {

    String arg = args[argIndex];
    String value = (argIndex + 1 < args.length) ? args[argIndex + 1] : null;
    Boolean valueRequired = true;
    Integer nextArgIndex = 0;

    nextArgIndex = argIndex + 2;

    switch (arg) {
      case "-f":
        setFilePath(value);
        break;
      case "-s":
        setSearchText(value);
        break;
      case "-i":
        setSearchColumnIndex(value);
        break;
      case "-c":
        setSearchColumnName(value);
        break;
      case "-h":
        setContainsHeader(true);
        valueRequired = false;
        nextArgIndex--;
        break;
      default:
        nextArgIndex -= 2;
        System.err.println(
            "Invalid argument: "
                + arg
                + ". Usage: -f FilePath -s SearchText [-i SearchColumnIndex"
                + " | -c SearchColumnName] [-h]");
        System.exit(1);
    }

    if (valueRequired && ((value == null) || value.startsWith("-") || (value.length() == 0))) {
      System.err.println(
          "Invalid value for '"
              + arg
              + "' flag. Usage: -f FilePath -s SearchText [-i SearchColumnIndex"
              + "| -c SearchColumnName] [-h]");
      System.exit(1);
    }

    return nextArgIndex;
  }

  /**
   * Set the file path for the CSV data
   *
   * @param filePath
   */
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Get the file path for the CSV data
   *
   * @return The file path as a String
   */
  public String getFilePath() {
    return this.filePath;
  }

  /**
   * Set the text to be searched for
   *
   * @param searchText: The text to be searched for in the CSV data
   */
  public void setSearchText(String searchText) {
    this.searchText = searchText;
  }

  /**
   * Get the text to be searched for
   *
   * @return the text to be searched for in the CSV data
   */
  public String getSearchText() {
    return this.searchText;
  }

  /**
   * Set the index of the column to be searched
   *
   * @param column
   */
  public void setSearchColumnIndex(String column) {
    try {
      this.searchColumnIndex = Integer.parseInt(column);
    } catch (Exception e) {
      System.err.println("Invalid column index");
      System.exit(1);
    }
  }

  /**
   * Get the index of the column to be searched
   *
   * @return column index to be searched
   */
  public Integer getSearchColumnIndex() {
    return this.searchColumnIndex;
  }

  /**
   * Set the name of the column to be searched
   *
   * @param column
   */
  public void setSearchColumnName(String column) {
    this.searchColumnName = column;
    this.setContainsHeader(true);
  }

  /**
   * Get the name of the column to be searched
   *
   * @return column name to be searched
   */
  public String getSearchColumnName() {
    return this.searchColumnName;
  }

  /**
   * Specify whether the CSV's first line is a header
   *
   * @param value
   */
  public void setContainsHeader(boolean value) {
    this.hasHeader = value;
  }

  /**
   * Return whether the CSV's first line is a header
   *
   * @return true if the CSV's first line is a header, false otherwise
   */
  public Boolean containsHeader() {
    return this.hasHeader;
  }

  /**
   * Check if the required parameters have been specified.
   *
   * @return true is all required parameters are provided.
   */
  public Boolean validArguments() {

    return (filePath != null)
        && (searchText != null)
        && (filePath.length() > 0)
        && (searchText.length() > 0);
  }
}
