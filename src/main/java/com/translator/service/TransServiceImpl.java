package com.translator.service;

import com.translator.config.InputSentence;
import com.translator.config.TranslateDTO;
import com.translator.repository.EnRankDictionary;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class TransServiceImpl implements TransService {

    @Autowired
    private EnRankDictionary erDict;    
    
    @Override
    public TranslateDTO findTranslation(InputSentence sentence) {
        
        String output = "";
        
        if(sentence.getSentence() != null) {
            String[] wordsToTrans = sentence.getSentence().split(" ");           

            Stream<String> translatedSentence = Arrays.stream(wordsToTrans);               

            if (sentence.isInQuotes()) {
                translatedSentence = translatedSentence.map(n -> '\u201C' + erDict.findTransWord(n) + '\u201C' );
            } else  {
                translatedSentence = translatedSentence.map(n -> erDict.findTransWord(n));
            }

            output = translatedSentence.collect(Collectors.joining(" "));
        }
        
        return new TranslateDTO(output);
    }   
}
