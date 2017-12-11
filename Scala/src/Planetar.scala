/**
 * Created by morgane on 11/12/17.
 */
class Planetar() extends Creature(){
  var hp:Int = 229
  var armor :Int = 32
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(27,22,17)
  var damageReduction = 0
  var spellResistance = 10
  val speed = 30
  val speedFly = 90

  //3D6+15
  val Epee_De : Int = 6
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 15


  /*val Epee_De : Int = 6
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 18
*/

  def computeDegasEpee() : Int = {
    val rnd = new scala.util.Random
    var degats = Epee_DegatsAditionnels
    var i = 0
    for(i  <- 1 to Epee_NombreDe){
      degats+=(1 + rnd.nextInt( (Epee_De - 1) + 1 ))
    }

    return degats
  }
}
