package JacksonDemo;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * author: dulei
 * date: 18-4-29
 * desc:
 */
public class JacksonDemo {
    private JsonGenerator jsonGenerator = null;
    private ObjectMapper objectMapper = null;
    private Book book = null;

    /**
     * Junit方法，用于给每个单元测试添加前置条件和结束条件
     */
    @Before
    public void init() {
        //构建一个Book实例对象
        book = new Book();
        book.setName("C语言程序设计");
        book.setAuthor("谭浩强");
        book.setBookId(33422);
        book.setPrice(30);
        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createGenerator(
                    System.out, JsonEncoding.UTF8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destory() {
        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }

            if(!jsonGenerator.isClosed()){
                jsonGenerator.close();
            }
            jsonGenerator = null;
            objectMapper = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**********************java常见数据类型转Json**********************/
    /**
     * 1.javaBean转换成json--writeObject/writeValue均可
     * 2.jsonGenerator依赖于ObjectMapper创建
     */
    @Test
    public void javaBeanToJson(){
       try {
           //方法1：
           System.out.println("jsonGenerator");
           jsonGenerator.writeObject(book);
           System.out.println();
           //方法2：
           System.out.println("ObjectMapper");
           objectMapper.writeValue(System.out, book);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    /**
     * List装换成Json,三种方式
     */
    @Test
    public void listToJson(){
        try {
            List<Book> list = new ArrayList<Book>();
            Book bookOne = new Book();
            bookOne.setName("安徒生童话");
            bookOne.setAuthor("安徒生");
            bookOne.setBookId(42532);
            bookOne.setPrice(55);
            list.add(bookOne);
            Book bookTwo = new Book();
            bookTwo.setName("阿拉丁神话");
            bookTwo.setAuthor("阿拉丁");
            bookTwo.setBookId(423423);
            bookTwo.setPrice(55);
            list.add(bookTwo);

            System.out.println("方式1：jsonGenretor");
            jsonGenerator.writeObject(list);
            System.out.println();
            System.out.println("方式2：ObjectMapper");
            System.out.println(objectMapper.writeValueAsString(list));
            System.out.println();
            System.out.println("方式3：直接通过objectMapper的writeValue方法");
            objectMapper.writeValue(System.out, list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * map转化成JSON,两种方式
     */
    @Test
    public void mapToJson(){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("book", book);
            Book newBook = new Book();
            newBook.setAuthor("安徒生");
            newBook.setName("安徒生童话");
            newBook.setBookId(34224);
            newBook.setPrice(55);
            map.put("newBook",book);
            System.out.println("第一种方式：jsonGenrator");
            jsonGenerator.writeObject(map);
            System.out.println();
            System.out.println("第二种方式：objectMapper");
            objectMapper.writeValue(System.out ,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /************************Json数据转换java数据*******************************/
    /**
     * json对象数据转化为JavaBean
     */
    @Test
    public void jsonToJavaBean(){
        String json = "{\"bookId\":11111, \"author\":\"周树人\",\"name\":\"朝花夕拾\",\"price\":45}";
        try {
            Book book = objectMapper.readValue(json, Book.class);
            System.out.println(book);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json数组转化为ArrayList
     *
     */
    @Test
    public void jsonToArrayList(){
        String json = "[{\"bookId\":11111,\"author\":\"鲁迅\",\"name\":\"野草\",\"price\":45}" +
                ",{\"bookId\":11221,\"author\":\"鲁迅\",\"name\":\"狂人日记\",\"price\":45}]";
        try {
            //方式一：读取的数组元素依然是数组
            Book [] books = objectMapper.readValue(json, Book[].class);
            for(int j = 0; j < books.length; j++){
                // 注意book[i]仅仅是数组，数组通过Arrays.asList()方法转化为ArrayList
                List<Book> list = Arrays.asList(books[j]);
                System.out.println(list);
            }
            //方式2：预定义类型按照List<Book>读取
            List<Book> books1 = objectMapper.readValue(json, new TypeReference<List<Book>>(){});
            System.out.println(books1);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json转化成map
     *
     */
    @Test
    public void JsonToMap(){
        String json = "{\"name\":\"book\",\"number\":12318,\"book\":{\"bookId\":12132,\"author\":\"鲁迅\",\"name\":\"朝花夕拾\"," +
                "\"price\":25}," +
                "\"book2\":{\"bookId\":22222,\"author\":\"易中天\",\"name\":\"祖先\",\"price\":25}}";
        try {
            Map<String,Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
            Set<String> key = maps.keySet();
            Iterator<String> iterator = key.iterator();
            while (iterator.hasNext()){
                String field = iterator.next();
                System.out.println(field + ":" + maps.get(field));
            }
            /**输出结果如下：
            name:book
            number:12318
            book:{bookId=12132, author=鲁迅, name=朝花夕拾, price=25}
            book2:{bookId=22222, author=易中天, name=祖先, price=25}
             */
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
