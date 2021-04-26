package com.translator.repository;

import com.translator.config.RankingDTO;
import java.util.List;

public interface EnRankDictionary {   
 
    String findTransWord(String originWord );
    
    List<RankingDTO> showRank();
    
}
