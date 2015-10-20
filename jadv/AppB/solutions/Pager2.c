/* File: Pager2.c */

#include <stdio.h>
#include "solutions_Pager2.h"
#include "LegacyPager.h"

JNIEXPORT void JNICALL Java_solutions_Pager2_page
    (JNIEnv *env, jobject obj, jstring message, jstring id)
{
  const char *localMessage =
    (*env)->GetStringUTFChars(env, message, NULL);
  const char *localId = (*env)->GetStringUTFChars(env, id, NULL);

  pageThem(localMessage, localId);

  (*env)->ReleaseStringUTFChars(env, message, localMessage);
  (*env)->ReleaseStringUTFChars(env, id, localId);
}

JNIEXPORT jstring JNICALL Java_solutions_Pager2_who
    (JNIEnv *env, jobject obj, jstring id)
{
  const char *localMessage = {"Isaac Newton"};
  return (*env)->NewStringUTF(env, localMessage);
}
