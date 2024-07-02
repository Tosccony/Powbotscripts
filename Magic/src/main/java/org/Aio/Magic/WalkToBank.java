package org.Aio.Magic;

import org.Aio.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.dax.api.models.RunescapeBank;

public class WalkToBank extends Task {

    public WalkToBank(){
        super();
        this.name = "WalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return !new lvls1to7().shouldExecute() && Bank.nearest().distance() > 10;
    }

    @Override
    public void execute() {
        System.out.println("Walking to the Bank");
        Movement.moveToBank(RunescapeBank.GRAND_EXCHANGE);
        System.out.println("Done Movement");
        Bank.open();
    }

}
