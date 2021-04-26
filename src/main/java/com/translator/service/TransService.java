
package com.translator.service;

import com.translator.config.InputSentence;
import com.translator.config.TranslateDTO;

public interface TransService {
    
    public TranslateDTO findTranslation(InputSentence sentence);
    
}
