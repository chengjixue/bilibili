package edu.xcj.edu.xcj.bilibili.api;

import edu.xcj.bilibili.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/23 0:02
 */
@RestController
public class DemoApi {
    @Resource
    private DemoService demoService;

    @GetMapping("/qurey")
    public Long qurey(Long id) {
        return demoService.qurey(id);
    }
}
