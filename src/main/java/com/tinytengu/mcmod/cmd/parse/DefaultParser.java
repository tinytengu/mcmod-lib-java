package com.tinytengu.mcmod.cmd.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default parser class
 * @author tinytengu
 */
public class DefaultParser implements Parser {
    /**
     * Parse source string and return parse result
     *
     * @param source Source string to parse
     * @return parse Result as ParseResult object
     */
    public ParseResult parse(String source) {
        // Basic cmd:args parsing
        Pattern pattern = Pattern.compile("^(?<cmd>[\\w\\d]+?)(?:[\\s]+(?<args>.+?))?$");
        Matcher matcher = pattern.matcher(source);

        ParseResult result = new ParseResult();

        if(!matcher.find()) {
            return result;
        }

        int end = -1;

        // Flags parsing
        result.name = matcher.group("cmd");

        if(matcher.group("args") == null) {
            return result;
        }

        pattern = Pattern.compile("-(.+?)[\\s=](.+?)(?=-.+?[\\s=].+?|$)");
        matcher = pattern.matcher(matcher.group("args"));

        while(matcher.find()) {
            if(end == -1)
                end = result.name.length() + matcher.start();

            result.flags.put(matcher.group(1), matcher.group(2).trim());
        }

        // Arguments parsing
        String[] args = source.substring(result.name.length(), end == -1 ? source.length() : end).trim().split(" ");
        result.args = (args.length == 1 && args[0].length() == 0) ? new String[]{} : args;

        return result;
    }
}