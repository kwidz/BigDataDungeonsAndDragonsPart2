/**
 * Created by morgane on 11/12/17.
 */
class MovanicDeva() extends Creature(){
  var hp:Int = 126
  var armor :Int = 24
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(17,12,7)
  var damageReduction = 0
  var spellResistance = 0
  val speed = 40
  val speedFly = 60


  //2D6+7
  val Epee_De : Int =6
  val Epee_NombreDe : Int = 2
  val Epee_DegatsAditionnels : Int = 7



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
