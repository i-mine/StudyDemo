import java.util.Date;

/**
 * author: dulei
 * date: 18-7-11
 * desc:
 */
public class StuForm {
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private boolean isMerried;

    public StuForm(String name, Integer age, Integer sex, Date birthday, boolean isMerried) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.isMerried = isMerried;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isMerried() {
        return isMerried;
    }

    public void setMerried(boolean merried) {
        isMerried = merried;
    }

    @Override
    public String toString() {
        return "StuForm{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", isMerried=" + isMerried +
                '}';
    }
}
