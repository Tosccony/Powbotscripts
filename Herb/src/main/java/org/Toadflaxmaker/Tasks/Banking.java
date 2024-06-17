package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.Input;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class Banking extends Task {

    public Banking() {
        super();
        this.name = "Banking";
    }

    public int NoteWidget = 12;
    public int Icon = 25;

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty() || !new Craft().shouldExecute() && !new Cleaning().shouldExecute() && !new Ge().shouldExecute();

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
}

