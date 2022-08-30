package com.peak.jacocodemo.test;

/**
 * @Desc
 * @Author hanson.hu
 * @Date 2022/8/29 17:39
*/
interface FinishListener {
    void onActivityFinished();
    void dumpIntermediateCoverage(String filePath);
}
