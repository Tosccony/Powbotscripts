package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.Toadflaxmaker.Toadflaxmaker;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.stream.widget.GeSlotStream;

import java.util.ArrayList;

public class Ge extends Task {

    Toadflaxmaker main;
    public Ge(Toadflaxmaker main) {
        super();
        this.name = "GE";
        this.main = main;
    }
    ArrayList<GeSlot> slots = GrandExchange.allSlots();

    @Override
    public boolean shouldExecute() {
        return Bank.stream().name("Grimy toadflax").isEmpty() && Bank.stream().name("Toadflax").isEmpty()
                && Inventory.stream().name("Grimy toadflax").isEmpty() && Inventory.stream().name("Toadflax").isEmpty()
                && Inventory.stream().name("Vial of water").isEmpty();

    }
    @Override
    public void execute() {
        if (Bank.close(true)){
            main.vials = (int) Inventory.stream().name("Vial of water").count(false);
            main.quantity_s = (int) Inventory.stream().name("Toadflax potion (unf)").count(true);

        }
        if (!GrandExchange.opened()) {
            GrandExchange.open();
            slots.forEach(slot -> {
                if (slots.get(0).isAvailable()) {
                    System.out.println("Checking if slot is available");
                    if (slots.get(0).isAvailable()) {
                        System.out.println("Selling potions");
                        GrandExchange.createOffer(main.pot, main.quantity_s, main.cost, main.shouldsell);
                        Condition.wait(()-> GrandExchange.currentSlot().isAvailable(), 150,10);
                    }
                    if (!slots.get(0).isAvailable()) {
                        System.out.println("Buying herbs");
                        GrandExchange.createOffer(main.herb, main.quantity, main.cost, main.shouldbuy);
                        Condition.wait(()-> GrandExchange.currentSlot().isAvailable(), 150,10);
                    }
                    if (!slots.get(1).isAvailable()) {
                        System.out.println("Buying Vials of water");
                        GrandExchange.createOffer(main.vial, main.quantity,main.vials_price, main.shouldbuy);
                        Condition.wait(() -> GrandExchange.stream().name("Vial of water").isNotEmpty(), 250, 25);
                        Condition.wait(()-> GrandExchange.currentSlot().isAvailable(), 150,10);
                    }
                }
            });
        }
        if (slots.get(1).isFinished()) {
            slots.forEach(slot -> {
                if (slots.get(1).isFinished()) {
                    System.out.println("Collecting items");
                    GrandExchange.collectAllToBank();
                    GrandExchange.close();
                }
            });
        } else if (!slots.get(1).isFinished()) {
            slots.forEach(slot -> {
                if (!slots.get(1).isFinished()) {
                    System.out.println("Items haven't bought, waiting");
                    Condition.wait(slot::isFinished, 150, 50);
                }
            });
        }
    }
}

















