/* File: StockMarketEngine.c */

#include <jni.h>
#include "examples_StockMarketEngine.h"

jobject listener[10];
int lCount = 0;

JNIEXPORT void JNICALL Java_examples_StockMarketEngine_addStockListener
  (JNIEnv * env, jobject obj, jobject javaStockListener)
{
  listener[lCount] = (*env)->NewGlobalRef(env,javaStockListener);
  lCount ++;
}

JNIEXPORT void JNICALL Java_examples_StockMarketEngine_startEngine
  (JNIEnv * env, jobject obj)
{
  jstring js;
  jclass jc;
  jmethodID mid;
  char * stockSymbol[10] = {"bh","itc","ms","y"};
  int x,y;
  for (x = 0; x < 4; x++)  {
    for (y=0; y < lCount; y++) {

      js = (*env)->NewStringUTF(env, stockSymbol[y]);
      jc = (*env)->GetObjectClass(env,listener[y]);
      mid = (* env)->GetMethodID
        (env, jc,"tradeNotification","(Ljava/lang/String;I)V");
      (*env)->CallVoidMethod(env,listener[y],mid,js,x*100);
	  (*env)->DeleteLocalRef(env, js);
    }
  }
}