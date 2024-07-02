package org.Aio.Crafting;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class Craftingbank extends Task {
    public Craftingbank() {
        super();
        this.name = "Craftingbank";
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty() || Inventory.stream().name("Molten glass").isEmpty();
    }

    @Override
    public void execute() {
        if(Bank.open()) {
            Bank.depositAllExcept("Glassblowing pipe");
            System.out.println("Finding Items");
            if (Inventory.stream().name("Glassblowing pipe").isEmpty()) {
                if (Bank.withdraw("Glassblowing pipe", 1)) {
                    Condition.wait(() -> Inventory.stream().name("Glassblowing pipe").isNotEmpty(), 150, 10);
                }
            } else if (Bank.withdraw("Molten glass", Bank.Amount.ALL)) {
                System.out.println("Withdrew Molten glass, waiting for update");
                Condition.wait(() -> Inventory.stream().name("Molten glass").isNotEmpty(), 100, 10);
            }
            if (Bank.opened()) {
                System.out.println("Banking.close()");
                Bank.close();
            }
        }
    }
}