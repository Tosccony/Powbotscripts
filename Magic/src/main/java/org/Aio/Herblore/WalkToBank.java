package org.Aio.Herblore;

import org.Aio.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.GrandExchange;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.dax.api.models.RunescapeBank;

public class WalkToBank extends Task {

    public WalkToBank(){
        super();
        this.name = "WalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return Bank.nearest().distance() > 5;
    }

    @Override
    public void execute() {
        if (Bank.nearest().distance()> 5)
            System.out.println("Walking to the Bank");
        Movement.moveToBank(RunescapeBank.GRAND_EXCHANGE);
        System.out.println("Done Movement");
        Bank.open();
    }
}