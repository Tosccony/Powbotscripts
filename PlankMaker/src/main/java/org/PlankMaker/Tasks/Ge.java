package org.PlankMaker.Tasks;

import org.PlankMaker.Planker;
import org.PlankMaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.GeSlot;
import org.powbot.api.rt4.GrandExchange;
import org.powbot.api.rt4.Inventory;

import java.util.ArrayList;

public class Ge extends Task {

    Planker main;
    public Ge(Planker main) {
        super();
        this.name = "GE";
        this.main = main;
    }
    ArrayList<GeSlot> slots = GrandExchange.allSlots();
    @Override
    public boolean shouldExecute() {
        return Bank.stream().name("Mahogany logs").isEmpty() && Inventory.stream().name("Mahogany logs").isEmpty();
               // && Inventory.stream().name("Mahogany plank").isNotEmpty() && Bank.stream().name("Mahogany plank").isEmpty();

    }
    @Override
    public void execute() {
        if (Bank.close(true)){
            main.plankcost = GrandExchange.getItemPrice("Mahogany plank");
        }
        if (!GrandExchange.opened()) {
            GrandExchange.open();
            slots.forEach(slot -> {
                if (slots.get(0).isAvailable()) {
                    System.out.println("Checking if slot is available");
                    if (slots.get(0).isAvailable()) {
                        System.out.println("Selling Planks");
                        GrandExchange.createOffer(main.plank, main.quantity_s, main.plankcost, main.shouldsell);
                        Condition.wait(()-> GrandExchange.currentSlot().isAvailable(), 150,10);
                    }
                    if (!slots.get(0).isAvailable()) {
                        System.out.println("Buying logs");
                        GrandExchange.createOffer(main.log, main.quantity, main.logcost, main.shouldbuy);
                        Condition.wait(()-> GrandExchange.currentSlot().isAvailable(), 150,10);
                    }
                    if (!slots.get(1).isAvailable()) {
                        System.out.println("Buying Vials of water");
                        GrandExchange.createOffer(main.nature, main.quantity,main.naturecost, main.shouldbuy);
                        Condition.wait(() -> GrandExchange.stream().name("Nature rune").isNotEmpty(), 250, 25);
                        Condition.wait(()-> GrandExchange.currentSlot().isAvailable(), 150,10);
                    }
                    if (!slots.get(2).isAvailable()) {
                        System.out.println("Buying Vials of water");
                        GrandExchange.createOffer(main.astral, 15000, main.astralcost, main.shouldbuy);
                        Condition.wait(() -> GrandExchange.stream().name("Astral rune").isNotEmpty(), 250, 25);
                        Condition.wait(() -> GrandExchange.currentSlot().isAvailable(), 150, 10);
                    }
                }
            });
        }
        if (slots.get(1).isFinished()) {
            slots.forEach(slot -> {
                if (slots.get(1).isFinished() && slots.get(2).isFinished() && slots.get(3).isFinished()) {
                    System.out.println("Collecting items");
                    GrandExchange.collectAllToBank();
                    GrandExchange.close();
                }
            });
        } else if (!slots.get(1).isFinished()) {
            slots.forEach(slot -> {
                if (!slots.get(1).isFinished() && !slots.get(2).isFinished() && !slots.get(3).isFinished()) {
                    System.out.println("Items haven't bought, waiting");
                    Condition.wait(slot::isFinished, 150, 50);
                }
            });
        }
    }
}