package JacksonDemo.JsonToJava;

import JacksonDemo.JavaToJson.Staff;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;

/**
 * author: dulei
 * date: 18-4-29
 * desc:
 */
public class JacksonOption {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();


//        将某个json转换为List
        String json = "[{\"workname\":\"dulei\"},{\"workname\":\"wangrui\"}]";
        try {
            List<Staff> list = mapper.readValue(json, new TypeReference<List<Staff>>(){});
            for (Staff s:list) {
                System.out.println(s.getName());
            }
            String jsonString = mapper.writeValueAsString(list);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }


//      JacksonOtion操作
        JacksonOption opt = new JacksonOption();
        opt.read2Java();

    }

    private void read2Java(){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{\"name\":\"dulei\",\"age\":23 ,\"salary\":7500, \"skill\":[\"java\",\"python\"]}";
        try {
            Staff staff = mapper.readValue(jsonInString, Staff.class);
            System.out.println(staff);

            //pretty print
            String prettyStaff = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(prettyStaff);

            //JsonNode直接获取指定字段，不需要Bean或者是POJO
            ObjectNode root = (ObjectNode) mapper.readTree(jsonInString);
            String name = root.get("name").asText();
            int age = root.get("age").asInt();
            System.out.println(name + ":" + age);

            //通过JsonNode去更改json字符串
            root.with("other").put("type","student");
            String modify_json = mapper.writeValueAsString(root);
            System.out.println(modify_json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
