package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 其他流的使用
 * 1.標準的輸入、輸出流
 * 2.打印流
 * 3.數據流
 *
 * @author USER
 * @date 2024-07-29 下午 09:09
 */
public class OtherStreamTest {
    /*
        1.標準的輸入、輸出流
        1.1
        System.in:標準的輸入流、預設鍵盤輸入
        System.out:標準的輸出流，預設控制台輸出
        
        1.2
        System類的setIn(InputStream is)/setOut(PrintStream ps)方式重新指定輸入和輸出的流。
        
        1.3 練習

        方法一；使用Scanner實現，調用next()返回一個字串

        方法二；使用System.in實現。System.in-----> 轉換流--->BufferedReader的readLine()



     */
    // IDEA不支持單測這樣寫法，改main測試
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            // 轉換流
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("請輸入字符串: ");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序結束");
                    break;
                }
                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    @Test
    public void test1() {
        BufferedReader br = null;
        try {
            // 轉換流
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("請輸入字符串: ");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序結束");
                    break;
                }
                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    /*
        3. 數據流

        3.1 DataInputStream DataOutStream
        3.2 作用: 用於讀取或寫出基本數據類型的變量或字符串

        練習: 將內存中字符串、基本數據類型的變量寫出到文件中。
        
        注意:異常處理仍應該用try-catch-finally.
     */
    @Test
    public void test3() throws IOException {
        //1.
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        //2.
        dos.writeUTF("廖小羊哈囉");
        dos.flush();// 只要flush刷新操作，就可以把內存中的數據寫入到文件
        dos.writeInt(23);
        dos.flush();// 只要flush刷新，就可以把內存中的數據寫入到文件
        dos.writeBoolean(true);
        dos.flush();// 只要flush刷新，就可以把內存中的數據寫入到文件

        //3.
        dos.close();

    }

    /*
        將文件中存儲的基本數據類型變量和字符串讀取道內存忠，保存在變量中。
        注意點：讀取不同數據類型有順序 ，要依照寫入保存的數據一致!
        
     */
    @Test
    public void test4() throws IOException {
        //1.
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        //2.
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());

        //3.
        dis.close();
    }

}
