package cn.dark.serializable.api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lwj
 * @date 2020-11-05
 */
public class StreamDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        map.put("0", "0");
        Map<String, String> map1 = new HashMap();
        map1.put("1", "1");
        Map<String, String> map2 = new HashMap();
        map2.put("2", "2");
        Map<String, String> map3 = new HashMap();
        map3.put("3", "3");
        List<Map<String, String>> list = Stream.of(map, map1, map2, map3).collect(Collectors.toList());

        Map<String, String> collect = list.stream().flatMap(item -> item.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);
    }

}
