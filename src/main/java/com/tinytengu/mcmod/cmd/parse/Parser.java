package com.tinytengu.mcmod.cmd.parse;

/**
 * Parser interface
 * @author tinytengu
 * */
public interface Parser {
    /**
     * Parse source string and return parse result
     *
     * @param source Source string to parse
     * @return parse Result as ParseResult object
     */
    ParseResult parse(String source);
}

