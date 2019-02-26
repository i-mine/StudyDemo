package JacksonDemo.JavaToJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.util.List;

/**
 * author: dulei
 * date: 18-4-29
 * desc:
 */
public class Staff {
//    json字符串中实际会以workname作为字段名
//    @JsonProperty("workname")
    private String name;
    private int age;
//    position 这一字段不会被写入到json字符串中
//    @JsonIgnore
    private String position;
    private BigDecimal salary;
    private List<String> skill;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", skill=" + skill +
                '}';
    }
}
