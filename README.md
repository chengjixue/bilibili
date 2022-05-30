

# bilibili

bilibili网站

##  热部署

###  设置idea

进入setting 勾选  

<img src="https://s2.loli.net/2022/05/23/WaNmEZf7LCrUjyi.png" alt="image-20220523185518642" style="zoom: 67%;" />



快捷键 ctrl+shift+alt+/调出界面

<img src="https://s2.loli.net/2022/05/23/Q3KxD8NtGjJbz97.png" alt="image-20220523190102387"  />

![image-20220523190647545](https://s2.loli.net/2022/05/23/T1ELqQuVSBJDAF9.png)

### 在pom文件添加

```xml
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
```

## 前端获取时间是一串数字的解决方法

因为项目使用的fastjson处理json数据属于阿里的jar包，所以使用spring自带的时间注解不能实现传值到前端的格式化问题

``` java
//spring自带的时间格式化注解
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//fastjson json数据格式化代码
@JSONField(format="yyyy-MM-dd HH:mm:ss")
```

