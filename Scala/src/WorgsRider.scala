case class WorgsRider() extends Creature (){
  var hp:Int = 13
  var armor :Int = 18
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(6)
  var damageReduction = 0
  var spellResistance = 0
  val speed = 20
  //1D8+2 * 3
  val Epee_De : Int = 8
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 3*2


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

  /*def computeDegasDist() : Int = {
    val rnd = new scala.util.Random
    var degats = Epee_DegatsAditionnels
    var i = 0
    for(i  <- 1 to Epee_NombreDe){
      degats+=(1 + rnd.nextInt( (Epee_De - 1) + 1 ))
    }

    return degats
  }*/

  override def getArmor(): Int = {
    return armor;
  }
}
