package com.translator;

import com.translator.config.InputSentence;
import com.translator.config.TranslateDTO;
import com.translator.repository.EnRankDictionary;
import com.translator.service.TransService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TranslatorApplicationTests {

    @Autowired
    private TransService transService;
    
    @Autowired
    private EnRankDictionary enRankDictionary;

    @Test
    public void shouldReturnTranslateWithoutQuote() {
        InputSentence inSent = new InputSentence("jesteś sterem białym żołnierzem", false);

        TranslateDTO transSent = transService.findTranslation(inSent);

        assertEquals( "you are the helm white soldier", transSent.getTrnsSentence());
    }

    @Test
    public void shouldReturnTranslateWithQuote() {
        InputSentence inSent = new InputSentence("ma kota", true);

        TranslateDTO transSent = transService.findTranslation(inSent);

        assertEquals('\u201C' + "has" + '\u201C' + " " + '\u201C' + "a cat" + '\u201C', transSent.getTrnsSentence());        
    }
    
    @Test
    public void shouldReturnEmptyStringWhenNull() {
        InputSentence inSent = new InputSentence(null, false);

        TranslateDTO transSent = transService.findTranslation(inSent);

        assertEquals("", transSent.getTrnsSentence());        
    }
    
    @Test
    public void shouldReturnGoodRankingOfWords() {
        InputSentence inSent1 = new InputSentence("Ala ma kota", false);
        InputSentence inSent2 = new InputSentence("ma kota", false);
        InputSentence inSent3 = new InputSentence("kota", false);

        transService.findTranslation(inSent1);
        transService.findTranslation(inSent2);
        transService.findTranslation(inSent3);
        
        int topRank = enRankDictionary.showRank().get(0).getRank();
        String topWord = enRankDictionary.showRank().get(0).getWord();
        assertEquals(3, topRank);
        assertEquals("a cat", topWord);  
    }

}
