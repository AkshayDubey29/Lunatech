package demo


import org.apache.spark.sql.DataFrame
import play.api.mvc._
import org.apache.spark.sql.SparkSession


/**
 * @author Alexandre Masselot.
 */


object HelloController extends Controller {

  val session = SparkSession.builder
    .master("local")
    .appName("Spark CSV Reader")
    .getOrCreate;
  val dataFile = "resources/tweet-json"
  lazy val rdd = session.read.format("csv").option("header","true")
      .csv("file:///C:/Users/akshayd/Downloads/ScalaProject/countries.csv")






  def index = Action {
    Ok("hello world")
  }

  /**
   * dataframe can output, with toJSON, a list of json string. They just need to be wrapped with [] and commas
   * @param rdd
   * @return
   */
  def toJsonString(rdd:DataFrame):String =
    "["+rdd.toJSON.collect.toList.mkString(",\n")+"]"

  /**
   * number of elements
   * @return
   */
  def count = Action {
    Ok(rdd.count.toString)

  }

  /**
   * list them all
   * @return
   */
  def list = Action {
    Ok(rdd.toDF().toString())
  }
  /**
   * make a fileter action on the dataframe element "text" field
   * @param text
   * @return
   */
  def filter(text:String) = Action {

    Ok(toJsonString(rdd.filter(rdd("text").contains(text))))
  }
}
