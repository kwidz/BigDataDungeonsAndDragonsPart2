
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

object Exercice1 {

  def functionMap(row:Any) = Some(row)

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR") // pour avoir moins de messages dans la console
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    //On veut faire un RDD a partir d'un fichier
    val df = spark.read.json("datas/data.json")
    df.show()
    df.withColumn("spells", explode(df("spells"))).show()

    //val result = df.flatMap()
    //df.printSchema()
    //myrdd.take(10).foreach(println)

    //Partie PairRDD enlevée :)


  }

}
