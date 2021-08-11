package com.tinytengu.mcmod.cmd;

import com.tinytengu.mcmod.cmd.parse.ParseResult;

import java.util.Arrays;

/**
 * Command class
 * @author tinytengu
 */
public class Command {
    /** Command name */
    public String name;

    /** Command hint */
    public String hint;

    /** Command parameters */
    public Parameter[] params;

    /** Command flags */
    public FlagsList flags;

    /** Ignore unexpected parameters and flags */
    public boolean ignoreUnexpected = false;

    /** Ignore empty required parameters and flags */
    public boolean ignoreRequired = false;

    /**
     * Creates new object
     * @param name Command name
     * @param hint Command hint
     */
    public Command(String name, String hint) {
        this.name = name;
        this.hint = hint;
        this.params = new Parameter[]{};
        this.flags = new FlagsList();
    }

    /**
     * Marshal parse result to command fields
     *
     * @param result parse result
     * @return marshal success status
     */
    public boolean marshal(ParseResult result) {
        // Set parameters
        for (int i = 0; i < result.args.length; i++) {
            try {
                // Set parameter value from passed argument
                this.params[i].setValue(result.args[i]);
            }
            // If parameter is not present
            catch(ArrayIndexOutOfBoundsException e) {
                // Skip if ignoreUnexpected is true
                if(this.ignoreUnexpected)
                    continue;

                // Else throw UnexpectedParameterException
                throw new UnexpectedParameterException(String.format("Unexpected parameter '%s' for command '%s'", result.args[i], this.name));
            }
        }

        for (int i = 0; i < this.params.length; i++) {
            if(this.params[i].getValue() == null) {
                // Fill null if parameter is optional
                if(this.params[i].optional) {
                    this.params[i].setValue(this.params[i].bydef);
                    continue;
                }
                // Skip parameter if ignoreRequired is true
                if(this.ignoreRequired) {
                    continue;
                }
                // Else throw RequiredParameterException
                throw new RequiredParameterException(this.hint.length() == 0 ?
                        String.format("Parameter '%d' required for command '%s'", i, this.name) :
                        String.format("Parameter '%s' required for command '%s'", this.params[i].hint, this.name)
                );
            }
        }

        // Set flags
        result.flags.forEach((key, value) -> {
            // Search for flag in command with `key` value name
            Flag flag = this.flags.find(key);

            // If flag is not present
            if(flag == null) {
                // And ignoreUnexpected is false
                if(!this.ignoreUnexpected)
                    // Throw UnexpectedFlagException
                    throw new UnexpectedFlagException(String.format("Unexpected flag '%s=%s' for command '%s'", key, value, this.name));
            } else {
                // Else set flag value from passed flags
                flag.setValue(value);
            }
        });

        // Fill null flags with default values
        for (Flag flag : this.flags) {
            // Skip flag if it's value not null
            if(flag.getValue() != null)
                continue;

            // Set flag default value if flag is optional
            if(flag.optional) {
                flag.setValue(flag.bydef);
                continue;
            }

            // Skip flag if it is null but ignoreRequired is true
            if(this.ignoreRequired)
                continue;

            // Else throw RequiredFlagException
            throw new RequiredFlagException(String.format("Flag '%s' required for command '%s'", flag.name, this.name));
        }
        return true;
    }

    /**
     * Execute command
     */
    public void execute() {
        this.onExecute();
        this.reset();
    }

    /**
     * Reset fields values to defaults
     */
    public void reset() {
        // Reset parameters
        for (Parameter param : this.params) {
            param.setValue(param.bydef);
        }

        // Reset flags
        for (Flag flag : this.flags) {
            flag.setValue(flag.bydef);
        }
    }

    /**
     * Execute callback
     */
    public void onExecute() {}

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", hint='" + hint + '\'' +
                ", params=" + Arrays.toString(params) +
                ", flags=" + flags +
                ", ignoreUnexpected=" + ignoreUnexpected +
                '}';
    }
}
