package org.TestLibrary.TestScript1.Tasks;

import org.TestLibrary.TestScript1.Task;
import org.powbot.api.Condition;
import org.powbot.api.Input;
import org.powbot.api.Notifications;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.mobile.script.ScriptManager;

public class Banking extends Task {

    public Banking(){
        super();
        this.name = "Banking";
    }

    @Override
    public boolean shouldExecute() {
        return Bank.nearest().distance()<10 && Inventory.stream().name("Lobster").isEmpty();

    }

    @Override
    public void execute() {
        System.out.println("Opening bank");
        if(Bank.open()){
            System.out.println("Finding Items");
            if(Bank.stream().name("Lobster").isEmpty()){
                System.out.println("No Lobsters in the bank, shutting down");
                Notifications.showNotification("No lobsters in the bank, shutting down");
                ScriptManager.INSTANCE.stop();
                return;
            } else {
                if(Bank.withdraw("Lobster", Bank.Amount.ALL)){
                    System.out.println("Withdrew lobsters waiting for update");
                    Condition.wait(()->Inventory.stream().name("Lobster").isNotEmpty(), 150, 10);
                }
            }
        }
        if(Bank.opened() && !shouldExecute()){
            if(Random.nextBoolean()) {
                System.out.println("Banking.close()");
                Bank.close();
            } else {
                System.out.println("Input.back()");
                Input.back();
            }

        }

    }
}
