package com.smallyang.java2;



import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Date;

/**
 * 一、集合框架的概述
 *
 * 1.集合、數組都是對多個數據進行存儲操作的結構，簡稱Java容器。
 *  說明：此時的存儲，主要指的是內存層面的存儲，不涉及到持久化的存儲(.txt,.jpg,.avi,數據庫中)
 *
 *
 * 2.
 *     2.1 數組在存儲多個數據方面的特點：
 *      > 一旦初始化以後，其長度就確定了。
 *      > 數組一旦定義好，其元素的類型也就確定了。我們也就只能操作指定類型的數據了。
 *          比如：String[] arr; int[] arr1;Object[] arr2;
 *     2.2 數組在存儲多個數據方面的缺點：
 *      > 一旦初始化以後，其長度就不可修改。
 *      > 數組中提供的方法非常有限，對於添加、刪除、插入數據等操作，非常不便，同時效率不高。
 *      > 獲取數組中實際元素的個數的需求，數組沒有現成的屬性或方法可用。
 *      > 數組存儲數據的特點：有序、可重複。對於無序、不可重複的需求，不能滿足。
 *
 * 二、集合框架
 *      |----Collection接口: 單列集合，用來存儲一個一個的對象
 *          |----List接口: 存儲有序、可重複的數據。 -->"動態"數組
 *              |----ArrayList、LinkedList、Vector
 *          |----Set接口: 存儲無序、不可重複的數據。 -->高中講的"集合"
 *              |----HasSet、LinkedHashSet、TreeSet
 *      |----Map接口: 雙列集合，用來存儲一對(key - value)一對的數據 -->高中函數:y=f(x)
 *              |----HashMap、LinkedHashMap、TreeMap、HashTable、Properties
 *
 * 三、Collection接口中的方法的使用
 *
 * 
 * 
 * @author USER
 * @date 2024-04-15 上午 06:39
 */
public class CollectionTest {

    @Test
    public void test1() {
        Collection coll = new ArrayDeque();

        // add(Object e):將元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);// 自動裝箱
        coll.add(new Date());

        // size()
        System.out.println(coll.size());// 4

        //  addAll(Collection coll1):將coll1集合中的元素添加到當前的集合中
        Collection coll1 = new ArrayDeque();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);
        System.out.println(coll.size());// 4
        System.out.println(coll);

        System.out.println(coll.isEmpty());

        // clear():清空集合元素
        coll.clear();

        // isEmpty():判斷當前集合是否為空
        System.out.println(coll.isEmpty());
    }


}

//    private List<TreatyTopQueryResponse.AccountingInfo> combineTreatyCodeInfoResponse(Map<TreatyKey, Map<SectionObject, List<TreatyQueryDTO>>> groupedByTreatyKey) {
//        List<TreatyTopQueryResponse.AccountingInfo> treatyCodeInfoList = new ArrayList<>();
//
//        // 遍历每个 TreatyKey 和对应的 Map<SectionObject, List<TreatyQueryDTO>>
//        groupedByTreatyKey.forEach((treatyKey, sectionMap) -> {
//            // 为每个 TreatyKey 创建一个新的 AccountingInfo 对象
//            TreatyTopQueryResponse.AccountingInfo treatyCodeInfo = accountTreatyQueryDTOMapper.toDto(treatyKey);
//
//            // 准备存放 SectionInfo 的列表
//            List<TreatyTopQueryResponse.SectionInfo> sectionInfoList = new ArrayList<>();
//
//            // 遍历 Map<SectionObject, List<TreatyQueryDTO>>
//            sectionMap.forEach((sectionObject, dtoList) -> {
//                // 准备存放所有 classField 的列表
//                List<String> classFields = new ArrayList<>();
//
//                // 收集所有相关的 classField
//                dtoList.forEach(dto -> classFields.add(dto.getClassField()));  // Assuming getClassField() returns a String
//
//                // 创建 SectionInfo 对象
//                TreatyTopQueryResponse.SectionInfo sectionInfo = new TreatyTopQueryResponse.SectionInfo(
//                        sectionObject.getSectionSeq(),
//                        sectionObject.getTreatySectionCode(),
//                        classFields // 将收集到的所有 classField 传递给 SectionInfo
//                );
//                // 添加到 sectionInfo 列表
//                sectionInfoList.add(sectionInfo);
//            });
//
//            // 将填充好的 SectionInfo 列表设置到当前 AccountingInfo 对象
//            treatyCodeInfo.setSectionInfoList(sectionInfoList);
//
//            // 将填充好的 AccountingInfo 对象添加到结果列表
//            treatyCodeInfoList.add(treatyCodeInfo);
//        });
//
//        return treatyCodeInfoList;
//    }

