//package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.powbot.api.Condition;
import org.powbot.api.Input;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.walking.model.Skill;

//public class Banking extends Task {

  //  public Banking() {
    //    super();
    //    this.name = "Banking";
 //   }

//    @Override
   // public boolean shouldExecute() {
      //  return //Inventory.isEmpty() || Skill.Woodcutting.realLevel() == 25 && Inventory.stream().name("Bronze axe").isNotEmpty()
             //   || Skill.Woodcutting.realLevel() == 31 && Inventory.stream().name("Mithril axe").isNotEmpty()
               // || !new Tree().shouldExecute() && !new Oak().shouldExecute() && !new Willow().shouldExecute();
  //  }

    //@Override
   // public void execute() {
     //   System.out.println("Opening bank");
       // if (Bank.open()) {
         //   Bank.depositInventory();
           // System.out.println("Finding Items");
           // if (Skill.Woodcutting.realLevel() < 15) {
             //   if (Bank.withdraw("Bronze axe",1)) {
               //     System.out.println("Withdrew Bronze, waiting for update");
                 //   Condition.wait(() -> Inventory.stream().name("Bronze axe").isNotEmpty(), 250, 10);
               // }
           // } else {
             //   if (Skill.Woodcutting.realLevel() == 21)  {
               //     if (Bank.withdraw("Mithril axe", 1)) {
                 //       System.out.println("Withdrew Mithril axe, waiting for update");
                   //     Condition.wait(() -> Inventory.stream().name("Mithril axe").isNotEmpty(), 25, 10);
             //       }
            //    }
           // } else {
          //      if (Skill.Woodcutting.realLevel() == 31) {
           //         if (Bank.withdraw("Adamant axe", 1)) {
             //           System.out.println("Withdrew Adamant axe, waiting for update");
             //           Condition.wait(() -> Inventory.stream().name("Adamant axe").isNotEmpty(), 25, 10);
              //      if (Skill.Woodcutting.realLevel() == 31) {
              //          Bank.withdraw("Adamant axe", 1);
                 //   }
               // if (Skill.Woodcutting.realLevel() )

              //      }
               //     }
             //   }
       //     }
     //   }
        //if (Bank.opened() && !shouldExecute()) {
           // if (Random.nextBoolean()) {
           //     System.out.println("Banking.close()");
           //     Bank.close();
           // } else {
             //   System.out.println("Input.back()");
            //    Input.back();
          //  }
     //   }
   // }
//}
