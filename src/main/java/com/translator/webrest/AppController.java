package com.translator.webrest;

import com.translator.config.InputSentence;
import com.translator.config.RankingDTO;
import com.translator.config.TranslateDTO;
import com.translator.repository.EnRankDictionary;
import com.translator.service.TransService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AppController {
    
    @Autowired
    private TransService transService;
    
    @Autowired
    private EnRankDictionary enRankDictionary;
    
    @RequestMapping(value="/translator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TranslateDTO> getTranslation(@RequestBody InputSentence input){
        return new ResponseEntity(transService.findTranslation(input), HttpStatus.OK );        
    }
    
    @RequestMapping(value="/rank", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<RankingDTO>> getRanking(){
        return new ResponseEntity(enRankDictionary.showRank(), HttpStatus.OK );        
    }
    
}
