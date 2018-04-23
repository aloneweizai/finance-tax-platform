package com.abc12366.uc.util;


/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-19
 * Time: 18:24
 */
public class RandomNumber {
    public static String getRandomNumber(int length) {
        if (length < 1) {
            return "-1";
        }
        String randomString;
        String returnNumber = "";
        for (int i = 0; i < length; i++) {
            randomString = Math.random() + "";
            int oneNumber = Integer.parseInt(randomString.substring(randomString.indexOf("0.") + 2, randomString
                    .indexOf("0.") + 3));
            System.out.println(oneNumber);
            returnNumber += oneNumber;
        }
        return returnNumber;
    }


    public static void main(String[] args) {
        //String str = Math.random()+"";
        //System.out.println(str.substring(str.indexOf("0.")+2, str.indexOf("0.") + 3));
        System.out.println(getRandomNumber(6));
    }
}
