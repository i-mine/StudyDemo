package JacksonDemo.JavaToJson;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * author: dulei
 * date: 18-4-29
 * desc:
 */
public class JacksonOption {
    public static void main(String[] args) {
        JacksonOption opt = new JacksonOption();
        opt.write2Json();
    }
    private void write2Json(){
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = createStaff();

        try {
            //将Java 对象转换为json String 直接写入文件
            mapper.writeValue(new File("/home/dulei/tmp/staff.json"), staff);
            //将Java 对象转换为json String
            String jsonString = mapper.writeValueAsString(staff);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Staff createStaff(){
        Staff staff = new Staff();
        List<String> skills = new ArrayList<String>();

        staff.setName("dulei");
        staff.setAge(24);
        staff.setPosition("BigData Engineer");
        staff.setSalary(new BigDecimal(8000));
        staff.setSkill(skills);

        skills.add("java");
        skills.add("python");

        return staff;
    }


}
