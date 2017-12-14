case class WarLord() extends Creature (){
  var hp:Int = 141
  var armor :Int = 27
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(20,15,10)
  var damageReduction = 0
  var spellResistance = 0
  val speed = 30
  //1D8+2 * 3
  val Epee_De : Int = 20
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 10


  val range_De : Int = 6
  val range_NombreDe : Int = 1
  val range_DegatsAditionnels : Int = 5


  def computeDegasEpee() : Int = {
    val rnd = new scala.util.Random
    var degats = Epee_DegatsAditionnels
    var i = 0
    for(i  <- 1 to Epee_NombreDe){
      degats+=(1 + rnd.nextInt( (Epee_De - 1) + 1 ))
    }

    return degats
  }
  def computeDegasRange() : Int = {
    val rnd = new scala.util.Random
    var degats = range_DegatsAditionnels
    var i = 0
    for(i  <- 1 to range_NombreDe){
      degats+=(1 + rnd.nextInt( (range_De - 1) + 1 ))
    }

    return degats
  }


  override def getArmor(): Int = {
    return armor;
  }
}
