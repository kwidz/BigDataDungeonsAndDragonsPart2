
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
      .appName("Spark D&D creatures by spells")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    //On veut faire un dataframe a partir d'un fichier
    val df = spark.read.json("datas/data.json")
    df.show()

    //On crée un rdd avec chaque ligne une créature et un sort
    val rdd = df.withColumn("spells", explode(df("spells")))
    //rdd.show()
    //On crée un dernier RDD avec sur chaque ligne un sort et un tableau de créatures qui possèdent ce sort
    //méthode avec map/réduce
    val rdd2a = rdd.rdd.map(row => (row.get(1),row.get(0))).reduceByKey((y,x) => (y,x))
    //On affiche les 20 premiers
    rdd2a.take(20).foreach(println)


    ////On crée un dernier RDD avec sur chaque ligne un sort et un tableau de créatures qui possèdent ce sort
    //méthode avec les outils de l'api spark
    val rdd2b = rdd.groupBy("spells").agg(collect_list(col("name")) as "Creature")
    rdd2b.show()
    rdd2b.rdd.saveAsTextFile("datas/rddSpells_Creatures")



  }

}
