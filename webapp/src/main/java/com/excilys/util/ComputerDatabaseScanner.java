package com.excilys.util;

import java.util.Scanner;

public class ComputerDatabaseScanner {
    private final Scanner scanner;
    private boolean exit;

    public ComputerDatabaseScanner() {
        scanner = new Scanner(System.in);
    }

    /**
     * Return true if the input contains a token.
     *
     * @return true if next token available, false otherwise
     */
    public boolean hasNextToken() {
        return scanner.hasNext();
    }

    /**
     * Return the next user's input.
     *
     * @return The next user's input
     */
    public String getNextToken() {
        return scanner.hasNext() ? scanner.next() : null;
    }

    /**
     * Return true if this scanner is stopped.
     *
     * @return True if this scanner is stopped, false otherwise
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Exit this scanner.
     *
     * @pre !isExit()
     * @post isExit()
     */
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
