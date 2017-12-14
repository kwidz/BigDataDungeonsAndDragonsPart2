/**
 * Created by morgane on 10/12/17.
 */
class AngelSlayer() extends Creature(){
  var hp:Int = 112
  var armor :Int = 26
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(21,16,11)
  var damageReduction = 0
  var spellResistance = 0
  val speed = 40

  //1D8+7 * 3
  val Epee_De : Int = 8
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 3*7


  def computeDegasEpee() : Int = {
    val rnd = new scala.util.Random
    var degats = Epee_DegatsAditionnels
    var i = 0
    for(i  <- 1 to Epee_NombreDe){
      degats+=(1 + rnd.nextInt( (Epee_De - 1) + 1 ))
    }

    return degats
  }


  override def getArmor(): Int = {
    return armor;
  }
}
