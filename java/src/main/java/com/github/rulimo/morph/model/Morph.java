package com.github.rulimo.morph.model;

/**
 *
 * @author Dmitriy Malakhov
 */
public class Morph {
    String word;
    String common;
    Rule rule;

    public Morph(String word, Rule rule) {
        this.word = word;
        this.common = word.substring(rule.pref.length(), word.length() - rule.suf.length());
        this.rule = rule;
    }

    public Form getRawForm() {
        return rule.items[0][0].getRaw();
    }
    
    public Form getNormForm() {
        return rule.items[0][0].getNorm();
    }
    
    public int getNormCount(){
        return rule.items.length;
    }
    
    public Form[] getRawForms(int normId) {
        Form[] forms = new Form[rule.items[normId].length];
        for (int i = 0; i < rule.items[normId].length; i++) {
            forms[i] = rule.items[normId][i].getRaw();
        }
        return forms;
    }
    
    public Form[] getNormForms(int normId) {
        Form[] forms = new Form[rule.items[normId].length];
        for (int i = 0; i < rule.items[normId].length; i++) {
            forms[i] = rule.items[normId][i].getNorm();
        }
        return forms;
    }
    
    public String getNorm() {
        return common + rule.items[0][0].getSuf();
    }
    
    public String getNorm(int normId) {
        return common + rule.items[normId][0].getSuf();
    }

    public String getRaw() {
        return word;
    }
    
    public boolean isStop(){
        for (int i = 0; i < rule.items.length; i++) {
            for (int j = 0; j < rule.items[i].length; j++) {
                if(rule.items[i][j].getRaw().isStop()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public String getSuf(){
        return rule.getSuf();
    }
    
    public String getPref(){
        return rule.getPref();
    }
}
