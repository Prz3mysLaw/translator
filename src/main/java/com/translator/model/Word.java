package com.translator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Word {
   String enMean;
   String plMean;
   int rank;
   
   public void pickUpRank(){
      rank++;
   }
}
