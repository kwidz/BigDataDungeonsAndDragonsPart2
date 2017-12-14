case class BarbareOrc() extends Creature (){
  var hp:Int = 142
  var armor :Int = 17
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(19,14,9)
  var damageReduction = 0
  var spellResistance = 0
  val speed = 40
  //1D8+2 * 3
  val Epee_De : Int = 8
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 30



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
