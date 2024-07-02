package org.Mining.Tasks;

import org.Mining.Mining;
import org.Mining.Task;
import org.powbot.api.Condition;
import org.powbot.api.Input;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.walking.model.Skill;

public class Banking extends Task {
    public Banking() {
        super();
        this.name = "Banking";
    }


    public boolean shouldExecute() {
    return Inventory.isEmpty() || Skill.Mining.realLevel() >= 21 && Inventory.stream().name("Bronze pickaxe").isNotEmpty()
            || Skill.Mining.realLevel() >= 31 && Inventory.stream().name("Adamant pickaxe").isEmpty()
            || Skill.Mining.realLevel() >= 41 && Inventory.stream().name("Rune pickaxe").isEmpty()
            || !new Miningfirst().shouldExecute() && !new Miningsecond().shouldExecute()
            && Bank.nearest().distance()< 10;
}

@Override
public void execute() {
    System.out.println("Opening bank");
    if (Bank.open()) {
        Bank.depositInventory();
        System.out.println("Finding Items");
        if (Skill.Mining.realLevel() < 15) {
            Bank.withdraw("Bronze pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Bronze pickaxe").isNotEmpty(), 250, 10);
        }
        if (Skill.Mining.realLevel() >= 21 && Skill.Mining.realLevel() <=30) {
            Bank.withdraw("Mithril pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Mithril pickaxe").isNotEmpty(), 250, 10);
        }
        if (Skill.Mining.realLevel() >= 31 && Skill.Mining.realLevel() <=40) {
            Bank.withdraw("Adamant pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Adamant pickaxe").isNotEmpty(), 250, 10);
        }
        if (Skill.Mining.realLevel() >= 41) {
            Bank.withdraw("Rune pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Rune pickaxe").isNotEmpty(), 250, 10);
        }
    }
      //  if (Random.nextBoolean()) {
         //   System.out.println("Banking.close()");
        //    Bank.close();
      //  } else {
         //   System.out.println("Input.back()");
         //   Input.back();
        }
    }

