package edu.brown.cs.student.main;

import edu.brown.cs.student.csv.Parser;
import edu.brown.cs.student.csv.Searcher;
import edu.brown.cs.student.csv.creator.CreatorFromRowSimple;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

/** Main class; the execution of the application begins here. */
public class Main {
  public static void main(String[] args) {
    // Organize the command line arguments into a structure that defines the purpose of each
    // argument and verifies the completeness of all arguments
    Args arguments = new Args(args);

    // Check if any required arguments are missing. If so, print an error message and exit.
    if (!arguments.validArguments()) {
      System.err.println(
          "Usage: -f FilePath -s SearchText [-i SearchColumnIndex | -c SearchColumnName] [-h]");
      System.exit(1);
    }

    // Create a Reader object linked to the CSV file
    Reader reader = null;
    try {
      reader = new BufferedReader(new FileReader(arguments.getFilePath()));

    } catch (Exception e) {
      System.err.println("Error while reading the CSV file: " + e.getMessage());
      System.exit(1);
    }

    // Create default formatter that leaves the row unchanged
    CreatorFromRowSimple rowSimple = new CreatorFromRowSimple();

    Parser<List<String>> parser =
        new Parser<List<String>>(reader, rowSimple, arguments.containsHeader(), ",");

    try {
      parser.Parse();
    } catch (Exception e) {
      System.err.println("Error while parsing the CSV data: " + e.getMessage());
      System.exit(1);
    }

    // Search results
    List<List<String>> searchResults = null;

    // Create a Searcher object to search the parsed records
    Searcher searcher = new Searcher(parser.getRecords());

    // Determine if a specific column should be searched
    Integer searchColumnIndex = arguments.getSearchColumnIndex();
    String searchColumnName = arguments.getSearchColumnName();

    if (searchColumnIndex >= 0) { // If user specifies a valid column index
      searchResults = searcher.findString(arguments.getSearchText(), searchColumnIndex);

    } else if (searchColumnName != null) { // If user specifies valid column name
      searchResults = searcher.findString(arguments.getSearchText(), searchColumnName);

    } else { // If user specifies no column
      searchResults = searcher.findString(arguments.getSearchText());
    }

    // Print the search results in the terminal
    if ((searchResults != null) && (searchResults.size() > 0)) {
      for (List<String> row : searchResults) {
        System.out.println(rowSimple.create(row));
      }
    } else {
      // Or, let the user know if no results are found
      System.out.println("No results found.");
    }
  }
}
