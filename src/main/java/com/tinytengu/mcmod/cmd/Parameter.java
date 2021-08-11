package com.tinytengu.mcmod.cmd;

/**
 * Parameter class
 * @author tinytengu
 */
public class Parameter extends Token {
    /**
     * Creates new object
     * @param hint Parameter hint
     *
     * @see Parameter#Parameter(String, String)
     * @see Parameter#Parameter(String, String, boolean) 
     */
    public Parameter(String hint) {
        super(hint);
    }

    /**
     * Creates new object
     * @param hint Parameter hint
     * @param bydef Parameter default value
     *
     * @see Parameter#Parameter(String)
     * @see Parameter#Parameter(String, String, boolean)
     */
    public Parameter(String hint, String bydef) {
        super(hint, bydef);
    }

    /**
     * Creates new object
     * @param hint Parameter hint
     * @param bydef Parameter default value
     * @param optional Is parameter optional
     *
     * @see Parameter#Parameter(String)
     * @see Parameter#Parameter(String, String)
     */
    public Parameter(String hint, String bydef, boolean optional) {
        super(hint, bydef, optional);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "hint='" + hint + '\'' +
                ", bydef='" + bydef + '\'' +
                ", optional=" + optional +
                '}';
    }
}
