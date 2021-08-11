package com.tinytengu.mcmod.cmd;

import com.tinytengu.mcmod.cmd.parse.DefaultParser;
import com.tinytengu.mcmod.cmd.parse.ParseResult;
import com.tinytengu.mcmod.cmd.parse.Parser;

import java.util.ArrayList;

/**
 * Handler class that handles source text and pass it to matching commands
 * @author tinytengu
 */
public class Handler {
    /** Commands list */
    public ArrayList<Command> commands;

    /** Source parser */
    public Parser parser;

    /**
     * Creates new object with initialized empty {@link Handler#commands} and {@link Handler#parser}
     *
     * @see Handler#Handler(ArrayList)
     * @see Handler#Handler(Parser)
     * @see Handler#Handler(ArrayList, Parser)
     */
    public Handler() {
        this.commands = new ArrayList<>();
        this.parser = new DefaultParser();
    }

    /**
     * Creates new object with passed {@link ArrayList} as {@link Handler#commands} and empty {@link Handler#parser}
     *
     * @param commands Commands list
     *
     * @see Handler#Handler()
     * @see Handler#Handler(Parser)
     * @see Handler#Handler(ArrayList, Parser)
     */
    public Handler(ArrayList<Command> commands) {
        this.commands = commands;
        this.parser = new DefaultParser();
    }

    /**
     * Creates new object with passed {@link Parser} as {@link Handler#parser} and empty {@link Handler#commands}
     *
     * @param parser Parser
     *
     * @see Handler#Handler()
     * @see Handler#Handler(ArrayList)
     * @see Handler#Handler(ArrayList, Parser)
     */
    public Handler(Parser parser) {
        this.commands = new ArrayList<>();
        this.parser = parser;
    }

    /**
     * Creates new object with passed {@link ArrayList} as {@link Handler#commands} and {@link Parser} as {@link Handler#parser}
     *
     * @param commands Commands
     * @param parser Parser
     *
     * @see Handler#Handler()
     * @see Handler#Handler(ArrayList)
     * @see Handler#Handler(ArrayList, Parser)
     */
    public Handler(ArrayList<Command> commands, Parser parser) {
        this.commands = commands;
        this.parser = parser;
    }

    /**
     * Parse source string and pass it to {@link Handler#commands}
     *
     * @param source Source string
     * @throws RuntimeException Exception
     */
    public void handle(String source) throws RuntimeException {
        ParseResult result = this.parser.parse(source);

        for (Command cmd : this.commands) {
            if(cmd.name == null || !cmd.name.equals(result.name))
                continue;

            if(cmd.marshal(result))
                continue;

            cmd.execute();
        }
    }
}