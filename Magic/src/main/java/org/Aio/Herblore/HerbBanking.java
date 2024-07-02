package org.Aio.Herblore;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.walking.model.Skill;

public class HerbBanking extends Task {
    public HerbBanking() {
        super();
        this.name = "Banking";
    }

    @Override
    public boolean shouldExecute() {
        return !new Cleaning().shouldExecute() || !new Crafting().shouldExecute();
    }

    @Override
    public void execute() {
        System.out.println("Opening bank");
        if (Bank.open()) {
            Bank.depositInventory();
            System.out.println("Finding Items");
            if (Bank.stream().name("Grimy guam leaf").isEmpty()) {
                if (Bank.withdraw("Guam leaf", 14))
                    if (Bank.withdraw("Vial of water", 14)) {
                        System.out.println("Withdrew Items, waiting for update");
                        Condition.wait(() -> Inventory.stream().name("Vial of water").isNotEmpty(), 150, 10);
                    }
            }
            if (Bank.stream().name("Grimy harralander").isEmpty()) {
                if (Bank.withdraw("Harralander", 14))
                    if (Bank.withdraw("Vial of water", 14)) {
                        System.out.println("Withdrew Items, waiting for update");
                        Condition.wait(() -> Inventory.stream().name("Vial of water").isNotEmpty(), 150, 10);
                    }
                }
            }
            if (Bank.withdraw("Grimy guam leaf", Bank.Amount.ALL)) {
                System.out.println("Withdrew Items, waiting for update");
                Condition.wait(() -> Inventory.stream().name("Grimy guam leaf").isNotEmpty(), 150, 10);
            } else {
                if (Bank.withdraw("Grimy harralander", Bank.Amount.ALL)) {
                    System.out.println("Withdrew Grimy harralander");
                    Condition.wait(() -> Inventory.stream().name("Grimy harralander").isNotEmpty(), 150, 10);
                }
            }
            if (Bank.close(false)) {
                System.out.println("Closing Bank and Starting our task");
                Bank.close();
            }
    }
}
