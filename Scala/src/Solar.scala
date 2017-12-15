class Solar() extends Creature() {
  var hp:Int = 363
  var regeneration :Int = 15
  var armor :Int = 44
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(35,30,25,20)
  var damageReduction = 15
  var spellResistance = 34
  val speed = 50
  //3D6 + 18
  val Epee_De : Int = 6
  val Epee_NombreDe : Int = 3
  val Epee_DegatsAditionnels : Int = 18


  val range_De : Int = 6
  val range_NombreDe : Int = 2
  val range_DegatsAditionnels : Int = 28


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

  override def getHP(): Int = {
    return hp
  }

  override def setHP(i: Int): Unit ={
    hp = i
  }
}
