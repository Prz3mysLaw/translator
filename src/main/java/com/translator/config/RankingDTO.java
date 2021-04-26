package com.translator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class RankingDTO {
    String word;
    int rank;    
}
