package com.github.rulimo.morph.model;


/**
 *
 * @author Dmitriy Malakhov
 */
public class RuleItem {
    private String suf;
    private Form norm;
    private Form raw;

    public RuleItem(String suf, Form norm, Form raw) {
        this.suf = suf.intern();
        this.norm = norm;
        this.raw = raw;
    }

    public String getSuf() {
        return suf;
    }

    public Form getNorm() {
        return norm;
    }

    public Form getRaw() {
        return raw;
    }
    
    public static RuleItem parse(String line) {
        String[] parts = line.split("/", -1);
        return new RuleItem(parts[0], new Form(parts[1]), new Form(parts[2]));
    }
    
    public static String serialize(RuleItem ruleItem) {
        StringBuilder sb = new StringBuilder();
        sb.append(ruleItem.suf).append("/")
                .append(ruleItem.norm).append("/")
                .append(ruleItem.raw);
        return sb.toString(); 
    }

    @Override
    public String toString() {
        return serialize(this);
    }
}
