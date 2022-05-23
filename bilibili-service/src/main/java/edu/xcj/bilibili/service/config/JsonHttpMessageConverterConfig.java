package edu.xcj.bilibili.service.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/23 20:09
 */
@Configuration
public class JsonHttpMessageConverterConfig {

    //测试循环引用
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        Object o = new Object();
        list.add(o);
        list.add(o);
        list.add(o);
        System.out.println(list.size());
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect));
    }

    @Bean
    @Primary
    public HttpMessageConverters fastJsonMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        自定义返回格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        对json数据序列化
        fastJsonConfig.setSerializerFeatures(
//                格式化输出数据
                SerializerFeature.PrettyFormat,
//                value为空的转为null传回前端
                SerializerFeature.WriteNullStringAsEmpty,
//                没有数据的list列表转为null
                SerializerFeature.WriteNullListAsEmpty,
                //                没有数据的Map列表转为null
                SerializerFeature.WriteMapNullValue,
//                对key和value进行排序（默认升序）
                SerializerFeature.MapSortField,
                SerializerFeature.DisableCircularReferenceDetect

        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }
}
