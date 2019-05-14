#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_xtm_qidashi_mingnews_net_JNI_stringFromJNI(JNIEnv *env, jclass type) {

    std::string key = "b791ab0abd2b9b9f7bd9e72de04fcbc0";
    return env->NewStringUTF(key.c_str());
}