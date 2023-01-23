package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.twosums.TwoSums;

@Slf4j
public class Main {

  public static void main(String[] args) {
    final TwoSums twoSums = new TwoSums();
    final int[] result = twoSums.calculate(new int[]{1, 2, 3}, 5);
    log.info("Result calculated. Result={}", result);
  }

}
