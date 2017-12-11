import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by morgane on 10/12/17.
 */
object Exercice2Combat2 {
  def main(args: Array[String]): Unit = {
    var graph: Graph[(Creature, Position), Long] = null

    def calculateDistance(positionA: Position, positionB: Position ) : Long = {
      return math.round(math.sqrt(math.pow(positionA.x-positionB.x,2)+math.pow(positionA.y-positionB.y,2)+math.pow(positionA.z-positionB.z,2)))

    }

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    var arrayRDD = new Array[(VertexId,(Creature, Position))](221)
    var arrayDesMechants = new Array[VertexId](211)
    var arrayDesGentils = new Array[VertexId](10)
    var arrayEdges = new Array[Edge[Long]](2110)




    var x = 0
    var y = 0
    var xsave = 0
    var ysave = 0
    var i = 0

    //Add solar
    arrayRDD.update(i,(i,(new Solar(),new Position(x,y,0))))
    arrayDesGentils.update(i,i)

    // Add 2 planetar et 2 movanic deva derriere solar
    i+=1
    arrayRDD.update(i,(i,(new Planetar(),new Position(-10,10,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new MovanicDeva(),new Position(-10,20,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new Planetar(),new Position(-10,-10,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new MovanicDeva(),new Position(-10,-20,0))))
    arrayDesGentils.update(i,i)

    //Add 5 astral deva
    i+=1
    arrayRDD.update(i,(i,(new AstralDeva(),new Position(-20,0,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new AstralDeva(),new Position(-20,10,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new AstralDeva(),new Position(-20,20,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new AstralDeva(),new Position(-20,-10,0))))
    arrayDesGentils.update(i,i)
    i+=1
    arrayRDD.update(i,(i,(new AstralDeva(),new Position(-20,-20,0))))
    arrayDesGentils.update(i,i)


var j = 0
    //Add 200 barbar orc
    for(x<- 100 until 200 by 10){
      for(y <- 100 until -100 by -10 ){
        i+=1
        arrayRDD.update(i,(i,(new BarbareOrc(),new Position(x,y,0))))
        arrayDesMechants.update(j,i)
        j += 1
      }
     xsave = x
    }

  // Add 10 Angel Slayer
    x = xsave + 10
    for(y <- 50 to -40 by -10 ){
      i+=1

      arrayRDD.update(i,(i,(new AngelSlayer(),new Position(x,y,0))))
      arrayDesMechants.update(j,i)
      j+=1
    }

    // add Green Great Wyrm Dragon
    x = x + 10
    i+=1
    arrayRDD.update(i,(i,(new DragonGreen(),new Position(x,0,0))))
    arrayDesMechants.update(j,i)


    val creatureRDD: RDD[(VertexId,(Creature, Position))] =  sc.parallelize(arrayRDD)

    var u,v,t = 0
    for(u <- 0 until arrayDesGentils.length){
      for(v <- 0 until arrayDesMechants.length){
        arrayEdges.update(t,Edge(u, v, calculateDistance(creatureRDD.lookup(v).head._2,creatureRDD.lookup(u).head._2)))
        t +=1

      }
    }


    val edges : RDD[Edge[Long]] = sc.parallelize(arrayEdges)

    graph = Graph(creatureRDD, edges)




  }


}
