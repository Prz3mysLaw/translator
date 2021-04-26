package com.translator.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.translator.config.RankingDTO;
import com.translator.model.DictWord;
import com.translator.model.Word;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;

public class Dictionary implements EnRankDictionary {

    List<Word> dict = new ArrayList();
//    Map<String, String> dict = new HashMap();
    ObjectMapper objectMapper = new ObjectMapper();

    public Dictionary() {      

        try {
            Map<String, String> mapOfWords = objectMapper.readValue(new ClassPathResource("./dict/dictionary.json")
                .getFile(), HashMap.class);
            
            dict = mapOfWords.entrySet().stream().map(x -> new Word(x.getValue(), x.getKey(), 0))
                    .collect(Collectors.toList()); 
                    
        } catch (FileNotFoundException ex){
            System.err.println("File does not exist");
            dict = Collections.emptyList();
        } 
        catch (IOException e) {
            System.err.println("File exists, but there was IOException");
            dict = Collections.emptyList();
        }      
    }

    @Override
    public String findTransWord(String originWord) {
        Word word = dict.stream()
                        .filter(x -> x.getPlMean().equalsIgnoreCase(originWord))
                        .findAny().orElse(new Word(" "," ",0));
        
        if (!word.getPlMean().contains(" ")) word.pickUpRank();
        
        return !word.getPlMean().contains(" ") ? word.getEnMean() : originWord;
    }

    @Override
    public List<RankingDTO> showRank() {
        return dict.stream()                
                   .map(x -> new RankingDTO(x.getEnMean(), x.getRank()))
                   .sorted(Comparator.comparingInt(RankingDTO::getRank).reversed())
                   .collect(Collectors.toList());
    }
    
}
