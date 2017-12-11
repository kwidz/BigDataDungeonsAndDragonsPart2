/**
 * Created by morgane on 11/12/17.
 */
class DragonGreen() extends Creature(){
  var hp:Int = 391
  var regeneration :Int = 0
  var armor :Int = 37
  var distanceRanged : Int = 110
  var distanceCaC : Int = 10
  val Attacks : Array[Int] = Array(35,30,25,20)
  var damageReduction = 0
  var spellResistance = 20
  val speed = 40
  val speedFly = 250


  // BITE 4D8 + 21
  val bite_de : Int = 6
  val bite_NombreDe : Int = 3
  val bite_DegatsAditionnels : Int = 18


  def computeDegasBite() : Int = {
    val rnd = new scala.util.Random
    var degats = bite_DegatsAditionnels
    var i = 0
    for(i  <- 1 to bite_NombreDe){
      degats+=(1 + rnd.nextInt( (bite_de - 1) + 1 ))
    }

    return degats
  }


  //  claws 4d6+14
  val claws_de : Int = 6
  val claws_NombreDe : Int = 4
  val claws_DegatsAditionnels : Int = 14


  def computeDegasClaws() : Int = {
    val rnd = new scala.util.Random
    var degats = claws_DegatsAditionnels
    var i = 0
    for(i  <- 1 to claws_NombreDe){
      degats+=(1 + rnd.nextInt( (claws_de - 1) + 1 ))
    }

    return degats
  }

  //wings 2d8+7
  val wings_de : Int = 8
  val wings_NombreDe : Int = 2
  val wings_DegatsAditionnels : Int = 7


  def computeDegasWings() : Int = {
    val rnd = new scala.util.Random
    var degats = wings_DegatsAditionnels
    var i = 0
    for(i  <- 1 to wings_NombreDe){
      degats+=(1 + rnd.nextInt( (wings_de - 1) + 1 ))
    }

    return degats
  }

  //tail slap 4d6+21
  val tail_de : Int = 6
  val tail_NombreDe : Int = 4
  val tail_DegatsAditionnels : Int = 21


  def computeDegasTailSlap() : Int = {
    val rnd = new scala.util.Random
    var degats = tail_DegatsAditionnels
    var i = 0
    for(i  <- 1 to tail_NombreDe){
      degats+=(1 + rnd.nextInt( (tail_de - 1) + 1 ))
    }

    return degats
  }
}
