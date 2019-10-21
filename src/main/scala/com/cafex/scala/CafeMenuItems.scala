package com.cafex.scala

sealed trait FoodType

case object Hot extends FoodType
case object Cold extends FoodType

abstract class CafeMenuItems {
  val name:String
  val amount:BigDecimal
  val foodType : FoodType

}

case class SandwichItem(name:String,amount:BigDecimal,foodType: FoodType) extends CafeMenuItems
case class DrinksItem(name:String,amount:BigDecimal,foodType: FoodType) extends CafeMenuItems

