/* File: Pager.c */

#include <stdio.h>
#include "solutions_Pager.h"
#include "LegacyPager.h"

JNIEXPORT void JNICALL Java_solutions_Pager_page
    (JNIEnv *env, jobject obj, jstring message, jstring id)
{
  const char *localMessage =
    (*env)->GetStringUTFChars(env, message, NULL);
  const char *localId = (*env)->GetStringUTFChars(env, id, NULL);

  pageThem(localMessage, localId);

  (*env)->ReleaseStringUTFChars(env, message, localMessage);
  (*env)->ReleaseStringUTFChars(env, id, localId);
}
