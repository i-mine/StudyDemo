package JacksonDemo

import com.google.gson.annotations.SerializedName
import com.google.gson.{Gson, JsonParser}

/**
  * author: dulei
  * date: 18-11-5
  * desc: 使用Gson对JsonArray进行序列化和反序列化操作
  * dependency:
  * <dependency>
  * <groupId>com.google.code.gson</groupId>
  * <artifactId>gson</artifactId>
  * <version>2.8.5</version>
  * </dependency>
  */
object GsonDemo {
  class Tag(){
    @SerializedName("1")
    var tag_1: String = _
    @SerializedName("2")
    var tag_2: String = _
    @SerializedName("id")
    var id: String = _
  }
  case class PkgInfo(date: String, package_name: String, tag: Array[Tag])
  def main(args: Array[String]): Unit = {
    val jsonStr =
      """
        |[{
        |        "date": "2018-01-31",
        |        "package_name": "com.peoplefun.wordcross",
        |        "tag": [
        |            {
        |                "1": "Games",
        |                "2": "Word",
        |                "id": "51"
        |            },
        |            {
        |                "1": "Games",
        |                "2": "Word",
        |                "id": "51"
        |            }
        |        ]
        |    }
        |]
      """.stripMargin
    val gson = new Gson()
    val jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray
    val array = gson.fromJson(jsonArray,classOf[Array[PkgInfo]])

    //反序列化
    array.foreach(
      node =>{
        println(node.package_name)
        println(node.date)
        node.tag.foreach(
          tag=> println(tag.tag_1,tag.tag_2,tag.id)
        )
      }
    )

    //序列化
    val str = gson.toJson(array)
    println(str)
  }

}
