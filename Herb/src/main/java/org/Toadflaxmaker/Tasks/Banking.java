package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.Toadflaxmaker.Toadflaxmaker;
import org.powbot.api.Condition;
import org.powbot.api.Input;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.mobile.Con;

public class Banking extends Task {
    Toadflaxmaker main;

    public Banking(Toadflaxmaker main) {
        super();
        this.name = "Banking";
        this.main = main;
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty() || !new Craft().shouldExecute() && !new Cleaning().shouldExecute();

        //return Inventory.isEmpty() || (Inventory.isFull() && Inventory.stream().name("Toadflax").count() == 28)
        //      || Inventory.stream().count() == 14 || Inventory.stream().name("Toadflax potion (unf)").count() == 28;

    }

    @Override
    public void execute() {
        System.out.println("Opening bank");
        if (Bank.open()) {
            Bank.depositInventory();
            System.out.println("Finding Items");
            if (Bank.stream().name("Grimy toadflax").isEmpty()) {
                if (Bank.withdraw("Toadflax", 14))
                    if (Bank.withdraw("Vial of water", 14)) {
                        System.out.println("Withdrew Items, waiting for update");
                        Condition.wait(() -> Inventory.stream().name("Vial of water").isNotEmpty(), 250, 10);
                    }
            } else {
                if (Bank.withdraw("Grimy toadflax", Bank.Amount.ALL)) {
                    System.out.println("Withdrew Items, waiting for update");
                    Condition.wait(() -> Inventory.stream().name("Grimy toadflax").isNotEmpty(), 350, 10);
                }
            }
            if (Bank.stream().name("Grimy toadflax").isEmpty() && Bank.stream().name("Toadflax").isEmpty()
                 && Inventory.stream().name("Grimy toadflax").isEmpty() && Inventory.stream().name("Toadflax").isEmpty()){
                Bank.depositInventory();
                Bank.withdrawModeNoted(true);
                if (Bank.withdrawModeNoted(true)) {
                    System.out.println("Withdrew Potions");
                    Bank.withdraw("Toadflax potion (unf)", Bank.Amount.ALL);
                    main.quantity_s = (int) Inventory.stream().name("Toadflax potion (unf)").count(true);
                    Condition.wait(() ->Inventory.stream().name("Toadflax potion (unf)").isNotEmpty(),1000,10);
                }
            }
            if (Random.nextBoolean()) {
                System.out.println("Banking.close()");
                Bank.close();
                Condition.wait(() -> Bank.close(true), 250,10);
            } else {
                System.out.println("Input.back()");
                Input.back();
                Condition.wait(() ->Bank.close(true), 250,10);
            }
        }
    }
}


