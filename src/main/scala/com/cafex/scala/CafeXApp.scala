package com.cafex.scala

import scala.annotation.tailrec

object CafeXApp extends App {

  import models._


  val billTotal =  tableOrder(args)
  println(f"$billTotal%1.2f")

  def tableOrder(tableOrderItems: Seq[String]): BigDecimal = {

    /*
    * */
    val cafeMenu: Map[String, CafeMenuItems] = Map[String, CafeMenuItems](
      "Cola" -> DrinksItem("Cola", .50, Cold),
      "Coffee" -> DrinksItem("Coffee", 1.00, Hot),
      "Cheese Sandwich" -> SandwichItem("Cheese Sandwich", 2.00, Cold),
      "Steak Sandwich" -> SandwichItem("Steak Sandwich", 4.50, Hot)
    )

    val validOrderItems = for (
      placedItem <- tableOrderItems
      if(cafeMenu.contains(placedItem))
    ) yield cafeMenu(placedItem)

      if(!validOrderItems.isEmpty) {
        val totalCharge:BigDecimal = applyServiceCharge(validOrderItems.foldLeft(BigDecimal(0))( _ + _.amount),identifyServiceCharge(validOrderItems))
        totalCharge
      }
      else 0
  }

  private def applyServiceCharge   (subTotal : BigDecimal , baseServiceCharge: BigDecimal) : BigDecimal = {

        val calculatedServiceCharge = subTotal * baseServiceCharge
        if(calculatedServiceCharge > 20) { subTotal+ 20 } else {subTotal + calculatedServiceCharge}

  }

  private def identifyServiceCharge(menuItems: Seq[CafeMenuItems]): BigDecimal = {

      // identify if the ordered items has Hot food
      val filteredMenuItems = menuItems.filter(!_.isInstanceOf[DrinksItem])

      if(filteredMenuItems.isEmpty) {
        0
      } else if(filteredMenuItems.exists(identifyHotSandwhich)) {
         .20
      } else {
        .10
      }

  }

  private def identifyHotSandwhich(items :CafeMenuItems) : Boolean = {
      items match {
         case SandwichItem(_,_,Hot) => true
         case _ => false
      }
  }

}