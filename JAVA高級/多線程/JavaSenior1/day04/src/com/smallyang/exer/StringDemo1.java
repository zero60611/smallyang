package com.smallyang.exer;

import org.junit.jupiter.api.Test;

/**
 * @author USER
 * @date 2024-01-30 下午 10:27
 */
public class StringDemo1 {
    /*
        獲取一個字符串在另一個字符串中出現的次數
     */

    /**
     * 獲取subStr在mainStr中出現的次數
     *
     * @param mainStr
     * @param subStr
     * @return
     */
    public int getCount(String mainStr, String subStr) {

        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;

        if (mainLength >= subLength) {
            // 方式一
//            while ((index = mainStr.indexOf(subStr)) != -1) {// 表示存在
//                count++;
//                //  有出現的字串出現在index，加上subString的長度，往後延伸變成新的mainStr
//                mainStr = mainStr.substring(index + subLength);
//            }
            // 方式2
            while ((index = mainStr.indexOf(subStr, index)) != -1) {// 表示存在
                count++;
                //  有出現的字串出現在index，加上subString的長度，往後延伸變成新的mainStr
                index+= subLength;
            }

            return count;
        } else {
            return 0;
        }


    }

    @Test
    public void testGetCount() {
        String mainStr = "abkkcadkavkebfhabbaaaababablab";
        String subStr = "ab";
        int count = getCount(mainStr, subStr);
        System.out.println(count);
    }
}
