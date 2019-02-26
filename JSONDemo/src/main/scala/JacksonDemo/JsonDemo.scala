package JacksonDemo

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}

import scala.collection.mutable.ListBuffer

/**
  * author: dulei
  * date: 18-11-2
  * desc: 
  */


object JsonDemo {

  case class Tag(tag_1: String, tag_2: String, id: String)

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
    val objectMapper = new ObjectMapper
    val pkgArray = objectMapper.readTree(jsonStr).elements()
    //遍历JsonArray
    while (pkgArray.hasNext) {

      val pkg = pkgArray.next()
      val date = pkg.get("date").asText()
      println(date)
      val package_name = pkg.get("package_name").asText()
      println(package_name)


      val tagList = ListBuffer[Tag]()
      val tags =pkg.get("tag").elements()
      //遍历tagArray
      while (tags.hasNext){
        val tag = tags.next()
        val tag_1 = tag.get("1").asText()
        val tag_2 = tag.get("2").asText()
        val id = tag.get("id").asText()
        println(tag_1,tag_2,id)
        tagList += Tag(tag_1, tag_2, id)
      }


    }


  }
}
