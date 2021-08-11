package com.tinytengu.mcmod.cmd;

/**
 * Flag class
 * @author tinytengu
 */
public class Flag extends Token {
    /** Flag name */
    public String name;

    /**
     * Creates new object
     * @param name Flag name
     * @param hint Flag hint
     *
     * @see Flag#Flag(String, String, String)
     * @see Flag#Flag(String, String, String, boolean)
     */
    public Flag(String name, String hint) {
        super(hint);
        this.name = name;
    }

    /**
     * Creates new object
     * @param name Flag name
     * @param hint Flag hint
     * @param bydef Flag default value
     *
     * @see Flag#Flag(String, String)
     * @see Flag#Flag(String, String, String, boolean)
     */
    public Flag(String name, String hint, String bydef) {
        super(hint, bydef);
        this.name = name;
    }

    /**
     * Creates new object
     * @param name Flag name
     * @param hint Flag hint
     * @param bydef Flag default value
     * @param optional Is flag optional
     *
     * @see Flag#Flag(String, String)
     * @see Flag#Flag(String, String, String)
     */
    public Flag(String name, String hint, String bydef, boolean optional) {
        super(hint, bydef, optional);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "name='" + name + '\'' +
                ", hint='" + hint + '\'' +
                ", bydef='" + bydef + '\'' +
                ", optional=" + optional +
                '}';
    }
}
