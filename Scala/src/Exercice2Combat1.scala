import org.apache.spark.graphx.VertexId
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Exercice2Combat1 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val creatureRDD: RDD[(VertexId,(Creature, Position))] =
      sc.parallelize(Array((1L,(new Solar(),new Position(0,0,0))),
        (2L,(new WorgsRider(),new Position(110,0,0))),
        (3L,(new WorgsRider(),new Position(110,-10,0))),
        (4L,(new WorgsRider(),new Position(115,-50,0))),
        (5L,(new WorgsRider(),new Position(115,-65,0))),
        (6L,(new WorgsRider(),new Position(100,-45,0))),
        (7L,(new WorgsRider(),new Position(105,10,0))),
        (8L,(new WorgsRider(),new Position(100,45,0))),
        (9L,(new WorgsRider(),new Position(115,65,0))),
        (10L,(new WorgsRider(),new Position(115,50,0))),
        (11L,(new WarLord(),new Position(200,0,0))),
        (12L,(new BarbareOrc(),new Position(150,10,0))),
        (13L,(new BarbareOrc(),new Position(150,0,0))),
        (14L,(new BarbareOrc(),new Position(150,-10,0))),
        (15L,(new BarbareOrc(),new Position(150,-200,0)))))



  }
}
