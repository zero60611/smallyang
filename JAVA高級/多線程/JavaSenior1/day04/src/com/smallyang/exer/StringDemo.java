package com.smallyang.exer;

import org.junit.jupiter.api.Test;

/**
 * @author USER
 * @date 2024-01-30 下午 09:38
 */
public class StringDemo {

    /*
    將一個字符串進行反轉
    方式一:轉換為char[]

     */
    public String reverse(String str, int startIndex, int endIndex) {

        if (str != null && str.length() != 0) {
            char[] arr = str.toCharArray();
            for (int x = startIndex, y = endIndex; x < y; x++, y--) {
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }

            return new String(arr);
        }
        return null;
    }

    /*
      方式二: 使用String的拼接
     */
    public String reverse1(String str, int startIndex, int endIndex) {
        if (str != null) {
            // 第一部分
            String reverseStr = str.substring(0, startIndex);
            // 第二部分
            for (int i = endIndex; i >= startIndex; i--) {
                reverseStr += str.charAt(i);
            }
            // 第三部分
            reverseStr += str.substring(endIndex + 1);
            return reverseStr;
        }
        return null;
    }

    /*
   方式三: 使用StringBuffer/StringBuilder的拼接
  */
    public String reverse2(String str, int startIndex, int endIndex) {
        if (str != null) {


            StringBuilder sb = new StringBuilder(str.length());
            // 第一部分
            sb.append(str.substring(0, startIndex));
            // 第二部分
            for (int i = endIndex; i >= startIndex; i--) {
                sb.append(str.charAt(i));
            }
            // 第三部分
            sb.append(str.substring(endIndex + 1));
            return sb.toString();
        }
        return null;
    }

    @Test
    public void testReverse() {
        String str = "abcdefg";
        String reverse = reverse2(str, 2, 5);
        System.out.println(reverse);
    }
}
