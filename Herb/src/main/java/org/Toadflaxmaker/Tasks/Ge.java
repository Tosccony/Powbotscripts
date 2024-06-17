package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.Toadflaxmaker.Toadflaxmaker;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import java.util.ArrayList;

public class Ge extends Task {
    ArrayList<GeSlot> slots = GrandExchange.allSlots();

    Toadflaxmaker main;
    public Ge(Toadflaxmaker main) {
        super();
        this.name = "GE";
        this.main = main;
    }

    @Override
    public boolean shouldExecute() {
        return Bank.stream().name("Grimy toadflax").isEmpty() && Bank.stream().name("Toadflax").isEmpty()
                && Inventory.stream().name("Grimy toadflax").isEmpty() && Inventory.stream().name("Toadflax").isEmpty();
    }

    @Override
    public void execute() {
        if (Inventory.stream().name("Toadflax potion (unf)").isEmpty()) {
            Bank.depositInventory();
            Bank.withdrawModeNoted(true);
            Bank.withdraw("Toadflax potion (unf)", Bank.Amount.ALL);
            Bank.close();
        } else {
            if (!GrandExchange.opened()) {
                GrandExchange.open();
                slots.forEach(slot -> {
                    if (slots.get(0).isAvailable()) {
                        System.out.println("PLEASE TELL ME THERES A FUCKING SLOT");
                        if (slots.get(0).isAvailable()) {
                            System.out.println("Selling potions");
                            GrandExchange.createOffer(pot, quantity_s, cost1, shouldsell);
                        }
                        if (!slots.get(0).isAvailable()) {
                            System.out.println("Buying herbs");
                            GrandExchange.createOffer(herb, quantity, cost, shouldbuy);
                        }
                        if (!slots.get(1).isAvailable()) {
                            System.out.println("Buying Vials of water");
                            GrandExchange.createOffer(vial, quantity, 6, shouldbuy);
                            Condition.wait(() -> GrandExchange.stream().name("Vial of water").isNotEmpty(), 250, 25);
                        }
                    }
                });
            }
            if (!GrandExchange.allSlots().isEmpty()) {
                slots.forEach(slot -> {
                    if (slots.get(1).isFinished()) {
                        System.out.println("Collecting items");
                        GrandExchange.collectAllToBank();
                        GrandExchange.close();
                        if (!GrandExchange.opened()) {
                            Bank.open();
                        }
                    } else {
                        if (!slots.get(1).isFinished()) {
                            System.out.println("Items haven't bought, waiting");
                            Condition.wait(slot::isFinished, 150, 50);
                        }
                    }
                });
            }
        }
    }
}

















