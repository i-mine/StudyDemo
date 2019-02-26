package JacksonDemo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * author: dulei
 * date: 18-4-29
 * desc:bean转json格式或者json转bean格式，项目中我们通常使用这个工具类进行json<-->java互相转换
 */
public class JacksonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator = new JsonFactory().createGenerator(stringWriter);
        mapper.writeValue(generator,obj);
        generator.close();
        return stringWriter.toString();
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) throws IOException {
        return mapper.readValue(jsonStr, objClass);
    }
}
