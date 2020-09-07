package com.github.rulimo.morph;

import com.github.rulimo.morph.model.Morph;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Проходим по всем словам в итераторе. Проверяем получение нормальных форм, общее количество слов и полученных нормальных форм
 * @author Dmitriy Malakhov
 */
public class MorphIteratorTest {
    
    @Test
    public void findTest() throws IOException{
        MorphIterator iterator = new MorphIterator();
        int count = 0;
        int normCount = 0;
        while(iterator.hasNext()){
            Morph m = iterator.next();
            Morph morpho = MorphAnalyzer.get(m.getRaw());
            if(morpho == null){
                throw new RuntimeException(m.getRaw());
            }
            if(morpho.getNormCount() != m.getNormCount()){
                throw new RuntimeException(m.getRaw());
            }
            normCount += morpho.getNormCount();
            count++;
        }
        assertEquals(count, 3034684);
        assertEquals(normCount, 3110284);
    }
}
