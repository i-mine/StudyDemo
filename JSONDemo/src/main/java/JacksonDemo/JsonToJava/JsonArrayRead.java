package JacksonDemo.JsonToJava;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * author: dulei
 * date: 18-8-8
 * desc:
 */
public class JsonArrayRead {
    @Test
    public void readJsonArrayTest() {
        ObjectMapper mapper = new ObjectMapper();
        String str = "[{\"date\":\"2018-08-06\",\"package_name\":\"com.ketchapp.knifehit\",\"tag\":[{\"1\":\"Games\",\"2\":\"Arcade\",\"id\":\"37\"}]},{\"date\":\"2018-07-01\",\"package_name\":\"com.animoca.google.starGirlBeautyQueen\",\"tag\":[{\"1\":\"Games\",\"2\":\"Role Playing\",\"id\":\"46\"}]},{\"date\":\"2018-04-10\",\"package_name\":\"fun.arts.studio.luck.wheel\",\"tag\":[{\"1\":\"Games\",\"2\":\"Trivia\",\"id\":\"50\"}]},{\"date\":\"2018-08-05\",\"package_name\":\"com.existage.v4\",\"tag\":[{\"1\":\"Games\",\"2\":\"Racing\",\"id\":\"45\"}]},{\"date\":\"2018-04-07\",\"package_name\":\"com.fatcat.BraidedHairstylesSalon\",\"tag\":[{\"1\":\"Games\",\"2\":\"Casual\",\"id\":\"41\"}]},{\"date\":\"2018-06-15\",\"package_name\":\"color.by.number.pixel.art.sandbox.paint.draw.free\",\"tag\":[{\"1\":\"Art & Design\",\"id\":\"2\"}]},{\"date\":\"2018-06-16\",\"package_name\":\"com.gamesforgirlsfree.cinderelladressup\",\"tag\":[{\"1\":\"Games\",\"2\":\"Casual\",\"id\":\"41\"}]},{\"date\":\"2018-07-25\",\"package_name\":\"com.avecreation.drivingzone2\",\"tag\":[{\"1\":\"Games\",\"2\":\"Racing\",\"id\":\"45\"}]},{\"date\":\"2018-06-27\",\"package_name\":\"com.outfit7.talkingtomgoldrun\",\"tag\":[{\"1\":\"Games\",\"2\":\"Action\",\"id\":\"35\"}]},{\"date\":\"2018-06-14\",\"package_name\":\"com.outfit7.mytalkingtomfree\",\"tag\":[{\"1\":\"Games\",\"2\":\"Casual\",\"id\":\"41\"}]},{\"date\":\"2018-06-18\",\"tag\":[{\"1\":\"Games\",\"2\":\"Racing\",\"id\":\"45\"}]},{\"date\":\"2018-07-28\",\"package_name\":\"com.avecreation.drivingzonegermany\",\"tag\":[{\"1\":\"Games\",\"2\":\"Racing\",\"id\":\"45\"}]}]";
        try {
            JsonNode nodes = mapper.readTree(str);
            StringBuilder stringBuilder = new StringBuilder();
            if (nodes.isArray()) {
                Iterator<JsonNode> iterator = nodes.elements();
                Map<String,String> treeMap = new TreeMap<String, String>(
                        new Comparator<String>() {
                            public int compare(String o1, String o2) {
                                return o2.compareTo(o1);
                            }
                        }
                );
                while (iterator.hasNext()) {
                    JsonNode jsonNode = iterator.next();
                    if(jsonNode.get("date") !=null && jsonNode.get("package_name") != null){
                        String date = jsonNode.get("date").asText();
                        String package_name = jsonNode.get("package_name").asText();
//                        stringBuilder.append(date + ":" + package_name);
                        treeMap.put(date,package_name);
//                        if (iterator.hasNext()) {
//                            stringBuilder.append("&");
//                        }
                    }

                }
                for (Map.Entry<String,String> map : treeMap.entrySet()){
                    System.out.println(map.getKey() + ":" + map.getValue() );
                }
            }
            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
