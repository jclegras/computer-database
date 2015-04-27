package com.excilys.console;

public interface IComputerDatabaseScanner {
    /**
     * Return true if the input contains a token.
     *
     * @return true if next token available, false otherwise
     */
    boolean hasNextToken();

    /**
     * Return the next user's input.
     *
     * @return The next user's input
     */
    String getNextToken();

    /**
     * Return true if this scanner is stopped.
     *
     * @return True if this scanner is stopped, false otherwise
     */
    boolean isExit();

    /**
     * Exit this scanner.
     *
     * @pre !isExit()
     * @post isExit()
     */
    void exit();
}
