/**
 * Created by morgane on 11/12/17.
 */
class AstralDeva() extends Creature(){
  var hp:Int = 172
  var armor :Int = 29
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(26,21,16)
  var damageReduction = 0
  var spellResistance = 0
  val speed = 50
  val speedFly = 100

  //1D8+14*3
  val Epee_De : Int = 8
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 3*14



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
