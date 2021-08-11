package com.tinytengu.mcmod.cmds;

import com.tinytengu.mcmod.cmd.Command;
import com.tinytengu.mcmod.cmd.Flag;
import com.tinytengu.mcmod.cmd.FlagsList;
import com.tinytengu.mcmod.cmd.Parameter;
import com.tinytengu.mcmod.cmd.parse.ParseResult;

/**
 * InitCommand class
 * @author tinytengu
 */
public class InitCommand extends Command {
    /**
     * Creates new object
     */
    public InitCommand() {
        super("init", "Initialize environment");

        this.params = new Parameter[] {
                new Parameter("Initialization path", ".", true)
        };

        this.flags = new FlagsList(new Flag[]{
                new Flag("mcver", "Minecraft version", "", true)
        });

        this.ignoreUnexpected = true;
    }

    @Override
    public boolean marshal(ParseResult result) {
        try {
            super.marshal(result);
        }
        catch(RuntimeException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    @Override
    public void onExecute() {
        System.out.println("init command executed");
        for (Parameter param : this.params) {
            System.out.printf("%s = %s\n", param.toString(), param.getValue());
        }
        for (Flag flag : this.flags) {
            System.out.printf("%s = %s\n", flag.toString(), flag.getValue());
        }
    }
}
