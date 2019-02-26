package JacksonDemo.SimpleDemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * author: dulei
 * date: 18-4-29
 * desc: 简单读取json和写入json的Demo
 */
public class Main {
    public static void main(String[] args) {
        //转换需要ObjectMapper,一次创建多次使用，可以用来读json,写json
        ObjectMapper mapper = new ObjectMapper();
        User user;
        String jsonString = "{\"name\":\"Bob\", \"age\":13}";
        try {
//            ========================json to java POJO=========================
//            第一种读取json方式：读取文件
//            User user = mapper.readValue(new File("data.json"), User.class);

//            第二种读取json方式：读取URL
//            user = mapper.readValue(new URL("http://someRestApi"),User.class);

//            第三种读取json方式: 读取Json字符串
            user = mapper.readValue(jsonString, User.class);
            System.out.println(user.name + ":" + user.age);

//            ========================java POJO to json=========================
//            第一种写入json的方式：写入字符串
            String newJsonString = mapper.writeValueAsString(user);
            System.out.println(newJsonString);

//            第二种写入json的方式：写入字节
            byte [] jsonByte = mapper.writeValueAsBytes(user);
            System.out.println(jsonByte.length);

//            第三种写入json的方式:写入文件
            mapper.writeValue(new File("/home/dulei/tmp/user.json"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
