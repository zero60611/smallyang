package com.smallyang.exer;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author USER
 * @date 2024-02-09 下午 11:03
 */
public class StringDemo2 {

    /*
        獲取兩個字符串中最大相同子串

        提示:將短的那串進行長度依次遞減的子串與較長的串做比較
     */
    //  前提兩字串只有一個最大相同
    public String getMaxSameString(String str1, String str2) {
        if (str1 != null && str2 != null) {
            String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
            String minStr = (str1.length() < str2.length()) ? str1 : str2;
            int length = minStr.length();

            for (int i = 0; i < length; i++) {
                for (int x = 0, y = length - i; y <= length; x++, y++) {
                    String sunStr = minStr.substring(x, y);
                    if (maxStr.contains(sunStr)) {
                        return sunStr;
                    }
                }
            }
        }
        return null;
    }

    public String[] getMaxSameString1(String str1, String str2){
        if (str1 != null && str2 != null) {
            StringBuffer sBuffer = new StringBuffer();
            String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
            String minStr = (str1.length() < str2.length()) ? str1 : str2;
            int length = minStr.length();

            for (int i = 0; i < length; i++) {
                for (int x = 0, y = length - i; y <= length; x++, y++) {
                    String sunStr = minStr.substring(x, y);
                    if (maxStr.contains(sunStr)) {
//                        return sunStr;
                        sBuffer.append(sunStr + ",");
                    }
                }
                System.out.println(sBuffer);
                if(sBuffer.length() != 0) {
                    break;
                }
            }
            String[] split = sBuffer.toString().replaceAll(",$", "").split("\\,");
            return split;
        }
        return null;
    }

    @Test
    public void test1() {
        String str1 = "anvdsdsahellouydsdadsa";
        String str2 = "cdvbhellonmdadsa";
//        String maxSameString = getMaxSameString(str1, str2);
        String[] maxSameString = getMaxSameString1(str1, str2);
        System.out.println(Arrays.toString(maxSameString));
    }

}
