package org.Aio.Fletching;

import org.Aio.Task;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.walking.model.Skill;

public class FletchBanking extends Task {
    public FletchBanking() {
        super();
        this.name = "FletchBanking";
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty();
    }

    @Override
    public void execute() {
        if (!Bank.opened()){
            Bank.open();
        }else {
            if (Bank.opened()) {
               if (Bank.withdraw("Arrow Shaft", Bank.Amount.ALL) && Bank.withdraw("Feather", Bank.Amount.ALL)) {
                System.out.println("Withdrawing Items");
               }
            }
            if (Bank.close(false)) {
                System.out.println("Closing Bank and Starting our task");
                Bank.close();
            }
        }

    }
}
