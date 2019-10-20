
import com.cafex.scala.CafeXApp
import org.scalatest._


class CafeXTestSpec extends WordSpec with Matchers {


  "CafeX Project Place Order" should {


    // Negative test cases
     "returns Zero" when {
        "empty Order is ordered" in {
            CafeXApp.tableOrder(Seq.empty) shouldBe 0
        }

       "invalid Order is ordered" in {
         CafeXApp.tableOrder(Seq("Cola2")) shouldBe 0
       }
     }

    // InValid Order Item Test
    "returns only for valid Order Item" when {


      "invalid second Order is ordered" in {
        CafeXApp.tableOrder(Seq("Cola","Coffe2")) shouldBe 0.50
      }

      "invalid third Order is ordered" in {
        CafeXApp.tableOrder(Seq("Cola","Coffe2","Cheese Sandwich")) shouldBe 2.75
      }

    }

    // Positive test cases
    "returns no service change for valid Drinks  Order Item" when {
      "Drinks Order is ordered" in {
        CafeXApp.tableOrder(Seq("Cola")) shouldBe 0.50
      }

      "Mutli Order is ordered" in {
        CafeXApp.tableOrder(Seq("Cola","Coffee")) shouldBe 1.50
      }
    }

     // Positive Service Charge order
    "returns service change for valid Drink and Sandwich  Order Item" when {
      "Drink and cold sandwhich Order is ordered" in {
        CafeXApp.tableOrder(Seq("Cola", "Cheese Sandwich")) shouldBe 2.75
      }

      "Drink and Hot Food Item are ordered" in {
        CafeXApp.tableOrder(Seq("Cola", "Steak Sandwich")) shouldBe 6
      }
    }

    "returns service change for single/multiple Sandwichs Order Item" when {
      "Both Cold and Hot Sandwhich Food is ordered" in {
        CafeXApp.tableOrder(Seq("Cheese Sandwich","Steak Sandwich")) shouldBe 7.80
      }

      //25 Hot Sandwhiches * £4.5 = £112.50 + (£112.50 * 0.2 > 20) = 132.50
      "bulk Hot Sandwhich order to maximise £ service charge" in {
        CafeXApp.tableOrder(Seq("Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich",
                                "Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich",
                                "Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich",
                                "Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich",
                                "Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich","Steak Sandwich"  )) shouldBe 132.50

      }
    }


  }



}
