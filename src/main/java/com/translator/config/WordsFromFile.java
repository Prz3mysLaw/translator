package com.translator.config;

import com.translator.model.DictWord;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WordsFromFile {
    List<DictWord> dictWordsList;
}
