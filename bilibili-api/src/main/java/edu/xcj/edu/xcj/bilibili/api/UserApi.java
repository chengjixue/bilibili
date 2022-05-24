package edu.xcj.edu.xcj.bilibili.api;

import com.fasterxml.jackson.databind.JsonNode;
import edu.xcj.bilibili.domain.JsonResponse;
import edu.xcj.bilibili.domain.User;
import edu.xcj.bilibili.service.UserService;
import edu.xcj.bilibili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/24 12:01
 */
@RestController
public class UserApi {
    @Autowired
    private UserService userService;
    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey(){
        String pk=RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }
    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return JsonResponse.success();
    }
    /*
     *
     * 登录
     * */
    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody User user){
       String token= userService.login(user);
       return new JsonResponse<>(token);
    }
}
