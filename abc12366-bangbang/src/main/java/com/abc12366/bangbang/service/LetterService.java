package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.LetterInsertBO;
import com.abc12366.bangbang.model.bo.LetterListBO;
import com.abc12366.bangbang.model.bo.LetterResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-24
 * Time: 17:40
 */
public interface LetterService {
    LetterResponse send(LetterInsertBO letterInsertBO, HttpServletRequest request) throws IOException;

    LetterListBO selectList(HttpServletRequest request) throws IOException;

    LetterResponse read(String id, HttpServletRequest request) throws IOException;

    LetterResponse update(String id, HttpServletRequest request) throws IOException;


    LetterResponse delete(String id, HttpServletRequest request) throws IOException;
}
