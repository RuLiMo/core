package com.github.rulimo.morph.model;

/**
 *
 * @author Dmitriy Malakhov
 */
public class Form implements Comparable<Form>{
    private Attr[] attrs;
    private String line;

    public Form(String line){
        String[] attrs = line.split(",");
        this.attrs = new Attr[attrs.length];
        for (int i = 0; i < attrs.length; i++) {
            this.attrs[i] = Attr.valueOf(attrs[i]);
        }
        this.line = line.intern();
    }

    public Attr[] getAttrs() {
        return attrs;
    }
    
    public boolean isStop(){
        if(attrs.length == 0){
            return false;
        }
        switch(attrs[0]){
            case NOUN: 
            case INFN: 
            case VERB:
            case ADJF:     
            case ADJS:     
            case ADVB:
            case PRTF:   
            case PRTS: 
            case COMP: 
            case GRND: return false;
        }
        return true;
    }

    @Override
    public int compareTo(Form o) {
        for (int i = 0; i < attrs.length && i < o.attrs.length; i++) {
            int res = attrs[i].ordinal() - o.attrs[i].ordinal();
            if(res != 0){
                return res;
            }
        }
        return attrs.length - o.attrs.length;
    }

    @Override
    public String toString() {
        return line;
    }
}
