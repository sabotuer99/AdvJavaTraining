/* File: LegacyCCCheck.c */

#include <string.h>
#include <stdio.h>

int ccCheck (char *string, int count){
  char graphic;
  int digits[40];
  int value = 0;
  int total = 0;
  int numberDigits = 0;
  int needsDoubling = 0;
  int odds[5] = {1,3,5,7,9};
  int i, j = 0;
  for (i = count-1; i >= 0; i--, j++) {
    graphic = string[i];
    value = graphic - 48;
    if (needsDoubling){
      needsDoubling = 0;
      if(value > 4){
        value = odds[value - 5];
      }
      else {
        value += value;
      }
    }
    else {
      needsDoubling = 1;
    }
    digits[j] = value;
    numberDigits++;
  }
  for (i = 0; i < numberDigits; i++){
    total += digits[i];
  }
  if (total % 10 == 0){
    return total;
  }
  else {
    return 0;
  }
}

