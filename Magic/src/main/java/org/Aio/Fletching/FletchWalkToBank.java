package org.Aio.Fletching;

import org.Aio.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.dax.api.models.RunescapeBank;

public class FletchWalkToBank extends Task {

    public FletchWalkToBank(){
        super();
        this.name = "FletchWalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return Bank.nearest().distance() > 5 && new FletchBanking().shouldExecute();
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