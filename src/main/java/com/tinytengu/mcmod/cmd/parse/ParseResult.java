package com.tinytengu.mcmod.cmd.parse;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Parse result class
 * @author tinytengu
 */
public class ParseResult {
    /** Command name field */
    public String name;

    /** Command arguments */
    public String[] args;

    /** Command flags */
    public HashMap<String, String> flags;

    /**
     * Creates new object with {@link ParseResult#name} as null, {@link ParseResult#args} as new String[]{}, {@link ParseResult#name} as HashMap<>()
     *
     * @see ParseResult#ParseResult(String, String[], HashMap)
     */
    public ParseResult() {
        this.name = null;
        this.args = new String[]{};
        this.flags = new HashMap<>();
    }

    /**
     * Creates new object
     *
     * @param name Command name
     * @param args Command arguments
     * @param flags Command flags
     *
     * @see ParseResult#ParseResult()
     */
    public ParseResult(String name, String[] args, HashMap<String, String> flags) {
        this.name = name;
        this.args = args;
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "ParseResult{" +
                "name='" + name + '\'' +
                ", args=" + Arrays.toString(args) +
                ", flags=" + flags +
                '}';
    }
}