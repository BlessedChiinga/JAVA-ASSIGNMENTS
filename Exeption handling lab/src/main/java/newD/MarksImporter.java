package newD;   // <-- delete this line if your class is NOT in a package

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class MarksImporter {

    private static final Logger LOGGER = Logger.getLogger(MarksImporter.class.getName());

    // Technical details go to import.log, not the console
    static {
        try {
            FileHandler fh = new FileHandler("import.log", true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            System.out.println("Warning: technical log file could not be created.");
        }
    }

    // ---------------- Activity B ----------------
    static void importMarks(String filename) throws IOException {
        Set<String> seen = new HashSet<>();

        // try-with-resources: br is closed automatically when this block ends,
        // even if an exception is thrown inside it.
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                lineNo++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    processRecord(line, lineNo, seen);
                } catch (DuplicateStudentException ex) {
                    System.out.println("Line " + lineNo + " skipped: " + ex.getMessage());
                    LOGGER.log(Level.WARNING, "Duplicate on line " + lineNo + " [" + line + "]", ex);
                } catch (NumberFormatException ex) {
                    System.out.println("Line " + lineNo + " skipped: mark is not a valid number.");
                    LOGGER.log(Level.WARNING, "Non-numeric mark on line " + lineNo + " [" + line + "]", ex);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Line " + lineNo + " skipped: " + ex.getMessage());
                    LOGGER.log(Level.WARNING, "Invalid record on line " + lineNo + " [" + line + "]", ex);
                }
            }
        }
    }

    private static void processRecord(String line, int lineNo, Set<String> seen) {
        String[] parts = line.split(",");

        // TODO 1
        if (parts.length != 2) {
            throw new IllegalArgumentException("expected 2 fields (ID, mark) but found " + parts.length);
        }

        String studentId = parts[0].trim();

        // TODO 2
        int mark = Integer.parseInt(parts[1].trim());
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("mark " + mark + " is out of range (0-100)");
        }

        // TODO 3
        if (!seen.add(studentId)) {
            throw new DuplicateStudentException("student " + studentId + " appears more than once");
        }

        // TODO 4
        System.out.println("Accepted: " + studentId + " -> " + mark);
    }

    // ---------------- Activity C ----------------
    public static void main(String[] args) {
        try {
            importMarks("marks.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Marks file not found; check the file name.");
            LOGGER.log(Level.SEVERE, "FileNotFoundException: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            System.out.println("The marks file could not be read. Please try again.");
            LOGGER.log(Level.SEVERE, "IOException: " + ex.getMessage(), ex);
        } catch (DuplicateStudentException ex) {
            System.out.println("Duplicate student found: " + ex.getMessage());
            LOGGER.log(Level.WARNING, "DuplicateStudentException", ex);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid data found in the marks file.");
            LOGGER.log(Level.WARNING, "IllegalArgumentException: " + ex.getMessage(), ex);
        } finally {
            System.out.println("Import attempt finished");
        }
    }
}

// Custom exception (unchecked, because it extends RuntimeException)
class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException(String message) {
        super(message);
    }
}