import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, graphx}

import scala.tools.cmd.Spec.Accumulator

object Exercice2Combat1 {
  val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
  val sc = new SparkContext(conf)
  var graph: Graph[(Creature, Position), Long] = null


  def calculateDistance(positionA: Position, positionB: Position ) : Long = {
    return math.round(math.sqrt(math.pow(positionA.x-positionB.x,2)+math.pow(positionA.y-positionB.y,2)+math.pow(positionA.z-positionB.z,2)))

  }


  def SendMsg(ctx : EdgeContext[(Creature, Position),Long,Long]):Unit = {
    // Calcul du touche

    val rnd = new scala.util.Random
    // A Ajouter est mon array de creature à parcourir
    val aAjouter = 10
    // lance du de 20
    val jetPourToucheDest = aAjouter + (1 + rnd.nextInt( (20 - 1) + 1 ))
    println("dst : " +  ctx.dstAttr._1.toString  +    " jetPourTouche : " + jetPourToucheDest)
    val jetPourToucheSourc = aAjouter + (1 + rnd.nextInt( (20 - 1) + 1 ))
    println("src : " +  ctx.srcAttr._1.toString  +     " jetPourTouche : " + jetPourToucheSourc)

if(ctx.dstAttr._1.getHP() > 0){
  // Attack de source vers dest
  if(jetPourToucheSourc > ctx.dstAttr._1.getArmor() ){
    val degat = ctx.srcAttr._1.computeDegasEpee()
    ctx.sendToDst(degat)
    ctx.dstAttr._1.setHP( ctx.dstAttr._1.getHP() - degat)
    println("src : " + ctx.srcAttr._1.toString +   " envoi " + degat + " degat à : " + ctx.dstAttr._1.toString + " qui a une armure de : " +ctx.dstAttr._1.getArmor())
  }else{
    ctx.sendToDst(0)
  }

  // Attack de dest vers sourc
  if(jetPourToucheDest > ctx.srcAttr._1.getArmor() ){
    val degat = ctx.dstAttr._1.computeDegasEpee()
    ctx.sendToSrc(degat)
  ctx.srcAttr._1.setHP( ctx.srcAttr._1.getHP() - degat)
    println("dst : " + ctx.dstAttr._1.toString +  " envoi " + degat + " degat à : " + ctx.srcAttr._1.toString + " qui a une armure de : " + ctx.srcAttr._1.getArmor() )
  }else{
    ctx.sendToSrc(0)
  }

}


  }

  def MergeMessage(l1: Long, l2:Long):Long ={

    if(l1 > l2) l1
    else l2
  }

  var fields = new TripletFields(true,true,false);

  def main(args: Array[String]): Unit = {



    //Cette accumalator va permette de savoir dans tous les threads si le solar a attaqué 4 fois ou non.

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
    val edges : RDD[Edge[Long]] =
      sc.parallelize(Array(Edge(1L, 2L, calculateDistance(creatureRDD.lookup(2L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 3L, calculateDistance(creatureRDD.lookup(3L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 4L, calculateDistance(creatureRDD.lookup(4L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 5L, calculateDistance(creatureRDD.lookup(5L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 6L, calculateDistance(creatureRDD.lookup(6L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 7L, calculateDistance(creatureRDD.lookup(7L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 8L, calculateDistance(creatureRDD.lookup(8L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 9L, calculateDistance(creatureRDD.lookup(9L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 10L, calculateDistance(creatureRDD.lookup(10L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 11L, calculateDistance(creatureRDD.lookup(11L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 12L, calculateDistance(creatureRDD.lookup(12L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 13L, calculateDistance(creatureRDD.lookup(13L).head._2,creatureRDD.lookup(1L).head._2)),
      Edge(1L, 14L, calculateDistance(creatureRDD.lookup(14L).head._2,creatureRDD.lookup(1L).head._2)),
        Edge(1L, 15L, calculateDistance(creatureRDD.lookup(15L).head._2,creatureRDD.lookup(1L).head._2))
      ))

    graph = Graph(creatureRDD, edges)
    graph.vertices.collect.foreach( vv => {
      println(s"vertexID : ${vv._1} node: ${vv._2}")
    })

    // Count all creatures
    println(graph.vertices.filter { case (id, (creature, position)) => creature.isInstanceOf[Solar] }.count)

    println(graph.vertices.filter { case (id, (creature, position)) => creature.isInstanceOf[BarbareOrc] }.count)

    println(graph.vertices.filter { case (id, (creature, position)) => creature.isInstanceOf[WarLord] }.count)


    // Count all the edges where src is Solar
    //println(graph.edges.filter(e => e.srcId == 1L).count)

    var bool = 10
    while (bool > 0 ){
      val message = graph.aggregateMessages[Long](SendMsg,MergeMessage,fields)

      if (message.isEmpty() ){
      return
      }
      else{
        graph = graph.joinVertices(message)((c,p,l) => p)
      }
      bool = bool -1

      println("FIN AGGREGATE")
      println("--------------------------------------")
      println("Mess Collecte : ")
      message.collect().foreach( println)
    }








  }
}
