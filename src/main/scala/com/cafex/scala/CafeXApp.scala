package com.cafex.scala

import scala.math.BigDecimal.RoundingMode

object CafeXApp extends App {


  def tableOrder(tableOrderItems: Seq[String]): BigDecimal = {

      val cafeMenu : Map[String, CafeMenuItems] = Map[String, CafeMenuItems](
        "Cola" -> DrinksItem("Cola", .50, Cold),
        "Coffee" -> DrinksItem("Coffee", 1.00, Hot),
        "Cheese Sandwich" -> SandwichItem("Cheese Sandwich", 2.00, Cold),
        "Steak Sandwich" -> SandwichItem("Steak Sandwich", 4.50, Hot)
      )

      val validOrderItems : Seq[CafeMenuItems] =  tableOrderItems.flatMap(cafeMenu get _)

        if(!validOrderItems.isEmpty) {
          val totalCharge:BigDecimal = calculateTotalCharge(validOrderItems,identifyServiceCharge)
          totalCharge.setScale(2,RoundingMode.HALF_UP)
        }
        else {
          // default total charge
          0
        }
  }

    private def calculateTotalCharge  (menuItems : Seq[CafeMenuItems] ,
                                       calServiceCharge: Seq[CafeMenuItems] => BigDecimal) : BigDecimal = {
      val subTotal:BigDecimal = menuItems.foldLeft(BigDecimal(0))( _ + _.amount)
      val calculatedServiceCharge:BigDecimal = subTotal * calServiceCharge(menuItems)
      if(calculatedServiceCharge > 20){
            subTotal+ 20
        }
        else {
          subTotal + calculatedServiceCharge
        }
    }


   def identifyServiceCharge(menuItems : Seq[CafeMenuItems]) : BigDecimal = {

      val filteredMenuItems : Seq[CafeMenuItems] = menuItems.filter(!_.isInstanceOf[DrinksItem])

      if(filteredMenuItems.isEmpty) {
        0
       } else if(filteredMenuItems.exists(identifyHotSandwhich)) { // identify if the ordered items has Hot food
         .20
      } else {
        .10
      }

  }

  private def identifyHotSandwhich(items : CafeMenuItems) : Boolean = {
      items match {
         case SandwichItem(_,_,Hot) => true
         case _ => false
      }
  }

}