package edu.xcj.edu.xcj.bilibili.api;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/23 14:59
 */
@RestController
public class RESTfulApi {
    //    存储数据
    private final Map<Integer, Map<String, Object>> dataMap;

    public RESTfulApi() {
        dataMap = new HashMap<>();
//        新建数据
        for (int i = 1; i < 3; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "name" + i);
            dataMap.put(i, data);
        }
    }

    @GetMapping("/objects/{id}")
    public Map<String, Object> getData(@PathVariable int id) {
        return dataMap.get(id);
    }

    @DeleteMapping("/objects/{id}")
    public String deleteData(@PathVariable int id) {
        dataMap.remove(id);
        return "delete succes";
    }

    @PostMapping("/objects")
    public String postData(@RequestBody Map<String, Object> data) {
        Integer[] integers = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(integers);
        int nextId = integers[integers.length - 1] + 1;
        dataMap.put(nextId,data);
        return "post succes";
    }
    @PutMapping("/objects")
    public String putData(@RequestBody Map<String, Object> data){
        Integer id=Integer.valueOf(String.valueOf(data.get("id")));
        Map<String, Object> containedData = dataMap.get(id);
        if (containedData==null){
            Integer[] integers = dataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(integers);
            int nextId = integers[integers.length - 1] + 1;
            dataMap.put(nextId,data);
        }else {
            dataMap.put(id, data);
        }
        return "put succes";
    }
}
