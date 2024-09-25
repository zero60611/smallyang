package com.smallyang;

import java.util.*;

public class Xmas {
    public static void main(String[] args) throws InterruptedException {
        // 使用Map儲存參與者及其對應的ID
        Map<String, String> participantsWithId = new LinkedHashMap<>();
        participantsWithId.put("丹尼", "1");
        participantsWithId.put("烏龍茶", "2");
        participantsWithId.put("初音毛", "3");
        participantsWithId.put("對打機", "4");
        participantsWithId.put("OOYXX", "5");
        participantsWithId.put("阿北獸借錢進化", "6");
        participantsWithId.put("YoMan", "7");
        participantsWithId.put("巴ruru", "8");
        participantsWithId.put("暫時飲所小ken", "9");
        participantsWithId.put("DK", "10");
        participantsWithId.put("多摩也博士", "11");
        participantsWithId.put("小幻", "12");
        participantsWithId.put("小明 劇透會變死媽獸", "13");
        participantsWithId.put("忍X狐", "14");
        participantsWithId.put("小瑋", "15");
        participantsWithId.put("米拉教徒", "16");
        participantsWithId.put("Eason", "17");
        participantsWithId.put("李溫", "18");
        participantsWithId.put("Gwie", "19");
        participantsWithId.put("螢火蟲", "20");
        participantsWithId.put("Fantastic", "21");
        participantsWithId.put("龜仙人", "22");
        participantsWithId.put("五條老師", "23");
        participantsWithId.put("ホーリードラチュウ", "24");
        participantsWithId.put("Liho", "25");
        participantsWithId.put("神聖縱火盤", "26");
        participantsWithId.put("毬毬", "27");
        participantsWithId.put("阿和", "28");
        participantsWithId.put("城", "29");
        participantsWithId.put("奈99", "30");
        participantsWithId.put("hao", "31");
        participantsWithId.put("Nunenunemon", "32");
        participantsWithId.put("TH", "33");
        participantsWithId.put("想讓外甥體驗怪獸對打機的99", "34");
        participantsWithId.put("廉價的的塔口餅", "35");

        Map<String, Integer> gifts = new LinkedHashMap<>();
        // (添加所有禮物清單)
        gifts.put("數碼寶貝快閃店集章卡明信片簡易護貝版 + 數碼寶貝快閃店滿額贈神聖計畫蓋章卡及進化鑰匙 + 數碼寶貝鑰匙圈(盲抽)", 4);
        gifts.put("2023 數碼寶貝快閃店滿額贈電車再見立牌", 1);
        gifts.put("數碼寶貝相卡 + 數碼寶貝快閃店拼圖(甲蟲獸、巴魯獸、加布獸、亞古獸)", 1);
        gifts.put("(加魯魯獸胸章 + 迪路獸胸章 + 海獅獸胸章 三個隨機擇一出)+ 怪獸對打機方塊自製軟殼", 3);
        gifts.put("一番賞杯墊(亞古獸+加布獸)", 1);
        gifts.put("元祖&超代保護套(螢光綠) * 1 個 + 支架 * 1 個 + 保護盒 * 1 個 +CR2032 * 1顆+LR44 * 2個", 6);
        gifts.put("A-COM", 1);
        gifts.put("X2抗體紫色", 1);
        gifts.put("怪獸對打機20th(顏色隨機)", 2);
        gifts.put("怪獸對打機20th日版改機(顏色隨機)", 1);
        gifts.put("彩機透明殼", 1);
        gifts.put("數碼寶貝02最新電影的明信片特典+電影的盲抽壓克力立牌特典+電影的數碼寶貝卡特典", 1);
        gifts.put("塔口-怪獸對打機方塊保護殼", 1);
        gifts.put("數碼寶貝卡牌卡雕", 1);
        System.out.println();
        System.out.println();
        // 打印聖誕樹
        printChristmasTree();

        // 分配禮物並顯示得獎名單
        List<String> remainingParticipants = new ArrayList<>(participantsWithId.keySet());
        Map<String, String> allocatedGifts = new HashMap<>();
        Random random = new Random();

        for (Map.Entry<String, Integer> gift : gifts.entrySet()) {
            for (int i = 0; i < gift.getValue(); i++) {
                if (!remainingParticipants.isEmpty()) {
                    int index = random.nextInt(remainingParticipants.size());
                    String participant = remainingParticipants.remove(index);
                    allocatedGifts.put(participant, gift.getKey());
                    System.out.println(gift.getKey() + " - " +  participantsWithId.get(participant) + " ： " +   participant );
                    Thread.sleep(1000);
                }
            }
        }

        // 顯示備取名單
        System.out.println("\n備取名單:");
        for (String backup : remainingParticipants) {
            System.out.println( participantsWithId.get(backup) + "-" +backup );
            Thread.sleep(1000);
        }
        // 打印聖誕樹
        printChristmasTree();
        System.out.println("聖誕快樂!!!");
    }

    private static void printChristmasTree() throws InterruptedException {
        int treeHeight = 10;
        for (int i = 0; i < treeHeight; i++) {
            for (int j = 0; j < treeHeight - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < (2 * i + 1); k++) {
                // 在樹的某些位置上添加裝飾
                if (k % 2 == 0) {
                    System.out.print("o"); // 代表燈泡
                } else {
                    System.out.print("*"); // 樹葉
                }
            }
            System.out.println();
        }
        // 畫出樹幹
        for (int i = 0; i < treeHeight / 2; i++) {
            for (int j = 0; j < treeHeight - 1; j++) {
                System.out.print(" ");
            }
            System.out.println("|"); // 樹幹
        }
        System.out.println();
        Thread.sleep(2000);
    }

}
