import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by morgane on 10/12/17.
 */
object Exercice2Combat2 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    var array = new Array[(VertexId,(Creature, Position))](221)




    var x = 0
    var y = 0
    var xsave = 0
    var ysave = 0
    var i = 0

    //Add solar
    array.update(i,(i,(new Solar(),new Position(x,y,0))))

    //Add 200 barbar orc
    for(x<- 100 until 200 by 10){
      for(y <- 100 until -100 by -10 ){
        i = i +1
        println(i)
        array.update(i,(i,(new BarbareOrc(),new Position(x,y,0))))
      }
     xsave = x
    }

  // Add 10 Angel Slayer
    x = xsave + 10
    for(y <- 50 to -40 by -10 ){
      array.update(i,(i,(new AngelSlayer(),new Position(x,y,0))))
    }

    // add Green Great Wyrm Dragon
    x = x + 10
    array.update(i,(i,(new DragonGreen(),new Position(x,0,0))))



    //2x Planetar
    //2x Movanic Deva
     // 5x Astral Deva







    val creatureRDD: RDD[(VertexId,(Creature, Position))] =
      sc.parallelize(array)
  }
}