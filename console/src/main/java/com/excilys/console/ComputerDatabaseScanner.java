package com.excilys.console;

import java.util.Scanner;

public class ComputerDatabaseScanner implements IComputerDatabaseScanner {
    private final Scanner scanner;
    private boolean exit;

    public ComputerDatabaseScanner() {
        scanner = new Scanner(System.in);
    }

    public boolean hasNextToken() {
        return scanner.hasNext();
    }

    public String getNextToken() {
        return scanner.hasNext() ? scanner.next() : null;
    }

    public boolean isExit() {
        return exit;
    }

    public void exit() {
        if (isExit()) {
            throw new IllegalStateException();
        }
        close();
        this.exit = true;
    }

    /*
     * Close the scanner.
     */
    private void close() {
        scanner.close();
    }
}
