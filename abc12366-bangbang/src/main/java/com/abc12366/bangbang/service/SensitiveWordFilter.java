package com.abc12366.bangbang.service;


import java.util.Map;
import java.util.Set;

public interface SensitiveWordFilter {

    Map getSensitiveWordMap();

    boolean isContaintSensitiveWord(String txt, int matchType);

    Set<String> getSensitiveWord(String txt, int matchType);

    String replaceSensitiveWord(String txt, int matchType, String replaceChar);

    String getReplaceChars(String replaceChar, int length);

    int CheckSensitiveWord(String txt, int beginIndex, int matchType);
}
