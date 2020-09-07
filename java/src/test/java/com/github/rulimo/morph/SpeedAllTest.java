package com.github.rulimo.morph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dmitriy Malakhov
 */
public class SpeedAllTest {
    List<String> words = new ArrayList<String>();
    @Before
    public void init(){
        MorphIterator iterator = new MorphIterator();
        while(iterator.hasNext()){
            words.add(iterator.next().getRaw());
        }    
    }
    
    @Test
    public void test() throws IOException{
        long time = System.currentTimeMillis();
        int i = 0;
        long hash = 0;
        for (int j = 0; j < 10; j++) {
            for (String word : words) {
                i++;
                hash += MorphAnalyzer.get(word).getRaw().length();
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) + " ms " + i + " " + hash);
    }
    
    @Test
    public void randomTest() throws IOException{
        long time = System.currentTimeMillis();
        long hash = 0;
        int itertions = 30000000;
        for (int j = 0; j < itertions; j++) {
            int i = (int)(Math.random() * words.size());
            hash += MorphAnalyzer.get(words.get(i)).getRaw().length();
        }
        System.out.println("Random Time: " + (System.currentTimeMillis() - time) + " ms " + 30000000 + " " + hash);
    }
    

}
