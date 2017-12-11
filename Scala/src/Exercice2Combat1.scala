import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, graphx}

object Exercice2Combat1 {

  def calculateDistance(positionA: Position, positionB: Position ) : Long = {
    return math.round(math.sqrt(math.pow(positionA.x-positionB.x,2)+math.pow(positionA.y-positionB.y,2)+math.pow(positionA.z-positionB.z,2)))

  }

  def doTurn(node: (graphx.VertexId, (Creature, Position)), graph: Graph[(Creature, Position), Long])={
    val beast = node._2._1
    println(beast)
   // graph.edges.filter { case Edge(src, dst, prop) => prop > 0 }.collect.foreach(println)
    //graph.edges.filter { case Edge(src, dst, prop) => prop < 110 }.collect.foreach(println)

  }

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

    val graph = Graph(creatureRDD, edges)
    // Count all creatures
    println(graph.vertices.filter { case (id, (creature, position)) => creature.isInstanceOf[Solar] }.count)

    println(graph.vertices.filter { case (id, (creature, position)) => creature.isInstanceOf[BarbareOrc] }.count)

    println(graph.vertices.filter { case (id, (creature, position)) => creature.isInstanceOf[WarLord] }.count)


    // Count all the edges where src is Solar
    //println(graph.edges.filter(e => e.srcId == 1L).count)

    creatureRDD.foreach(f => doTurn(f, graph))







  }
}
