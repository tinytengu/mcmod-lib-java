package com.tinytengu.mcmod.cmd;

/**
 * Command token class
 * @author tinytengu
 */
public class Token {
    /** Token hint */
    public String hint;

    /** Token default value */
    public String bydef = "";

    /** Is token optional */
    public boolean optional = false;

    /** Token value */
    private String value;

    /**
     * Creates new object
     * @param hint Token hint
     *
     * @see Token#Token(String, String)
     * @see Token#Token(String, String, boolean)
     */
    public Token(String hint) {
        this.hint = hint;
    }

    /**
     * Create new object
     * @param hint Token hint
     * @param bydef Token default value
     *
     * @see Token#Token(String)
     * @see Token#Token(String, String, boolean)
     */
    public Token(String hint, String bydef) {
        this.hint = hint;
        this.bydef = bydef;
    }

    /**
     * Create new object
     * @param hint Token hint
     * @param bydef Token default value
     * @param optional Is token optional
     *
     * @see Token#Token(String)
     * @see Token#Token(String, String)
     */
    public Token(String hint, String bydef, boolean optional) {
        this.hint = hint;
        this.bydef = bydef;
        this.optional = optional;
    }

    /**
     * Get token value
     * @return {@link Token#value}
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set token value
     * @param value new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "hint='" + hint + '\'' +
                ", bydef='" + bydef + '\'' +
                ", optional=" + optional +
                ", value='" + value + '\'' +
                '}';
    }
}
