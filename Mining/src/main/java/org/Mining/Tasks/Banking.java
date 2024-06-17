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
    return Inventory.isEmpty() || Skill.Mining.realLevel() == 21 && Inventory.stream().name("Bronze pickaxe").isNotEmpty()
           // || Skill.Woodcutting.realLevel() == 31 && Inventory.stream().name("Mithril axe").isNotEmpty()
            || !new Miningfirst().shouldExecute(); //&& !new Oak().shouldExecute() && !new Willow().shouldExecute() && !new Teak().shouldExecute();
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
        if (Skill.Mining.realLevel() == 21) {
            Bank.withdraw("Mithril pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Mithril pickaxe").isNotEmpty(), 250, 10);
        }
        if (Skill.Mining.realLevel() == 31) {
            Bank.withdraw("Adamant pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Adamant pickaxe").isNotEmpty(), 250, 10);
        }
        if (Skill.Mining.realLevel() == 41) {
            Bank.withdraw("Rune pickaxe", 1);
            Condition.wait(() -> Inventory.stream().name("Rune pickaxe").isNotEmpty(), 250, 10);
        }
    }
    if (Bank.opened() && !shouldExecute()) {
        if (Random.nextBoolean()) {
            System.out.println("Banking.close()");
            Bank.close();
        } else {
            System.out.println("Input.back()");
            Input.back();
        }
    }
}
}