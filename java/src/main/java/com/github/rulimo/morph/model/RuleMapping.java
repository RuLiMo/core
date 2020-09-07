package com.github.rulimo.morph.model;

import com.github.rulimo.Configuration;
import gnu.trove.map.hash.TObjectIntHashMap;
import java.util.Set;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Dmitriy Malakhov
 */
public class RuleMapping {

    String suf;
    TObjectIntHashMap<String> prefcommons;
    Rule[] rules;

    
    public RuleMapping(String suf, TObjectIntHashMap<String> prefcommons, Rule[] rules) {
        this.prefcommons = prefcommons;
        this.rules = rules;
        this.suf = suf;
    }
    
    public static RuleMapping parse(String str){
        String[] parts = str.split("\t", -1);
        String suf = parts[0];
        Rule[] rules = new Rule[parseInt(parts[1])];
        for (int i = 0; i < rules.length; i++) {
            rules[i] = Rule.parse(parts[2 + i]);
        }
        TObjectIntHashMap<String> prefcommons = new TObjectIntHashMap<String>((parts.length - 2 + rules.length) / 2, Configuration.getMorphMappingLoadFactor(), -1);
        for (int i = 2 + rules.length; i < parts.length; i+=2) {
            prefcommons.put(parts[i].intern(), parseInt(parts[i + 1]));
        }
        return new RuleMapping(suf, prefcommons, rules);
    }

    public Set<String> getPrefcommons() {
        return prefcommons.keySet();
    }

    public Rule[] getRules() {
        return rules;
    }
    
    public String getSuf() {
        return suf;
    }

    public Rule getRule(String key) {
        int index = prefcommons.get(key);
        if(index < 0){
            return null;
        }
        return rules[index];
    }
    
    public static String serialize(RuleMapping mapping){
        StringBuilder sb = new StringBuilder();
        sb.append(mapping.suf).append("\t").append(mapping.rules.length);
        for (Rule rule : mapping.rules) {
            sb.append("\t").append(Rule.serialize(rule));
        }
        for (String prefcommon : mapping.prefcommons.keySet()) {
            sb.append("\t").append(prefcommon);
            sb.append("\t").append(mapping.prefcommons.get(prefcommon));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return serialize(this);
    }
    
    
}
