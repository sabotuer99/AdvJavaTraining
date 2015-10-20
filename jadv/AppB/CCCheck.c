/* File: CCCheck.c */

#include "examples_CCCheck.h"
#include <string.h>
#include "LegacyCCCheck.h"

JNIEXPORT jint JNICALL Java_examples_CCCheck_validCC
    (JNIEnv *env, jobject obj, jstring ccNumber, jint count) {
  char cardNumber[40];
  /* Convert Java String to C char array for reference */
  const char *str = (*env)->GetStringUTFChars(env, ccNumber, NULL);

  int valid = 0;
  strcpy(cardNumber, str);
  valid = ccCheck(cardNumber, count);
  (*env)->ReleaseStringUTFChars(env, ccNumber, str);

  return valid;
}