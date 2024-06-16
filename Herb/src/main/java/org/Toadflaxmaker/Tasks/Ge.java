package org.Toadflaxmaker.Tasks;


import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
public class Ge extends Task {
    ArrayList<GeSlot> slots = GrandExchange.allSlots();

    public Ge() {
        super();
        this.name = "GE";
    }

    public int NoteWidget = 12;
    public int Icon = 25;

    @Override
    public boolean shouldExecute() {
        return Bank.stream().name("Grimy toadflax").isEmpty() && Bank.stream().name("Toadflax").isEmpty()
                && Inventory.stream().name("Grimy toadflax").isEmpty() && Inventory.stream().name("Toadflax").isEmpty();
    }

    @Override
    public void execute() {
        int cost = GrandExchange.getItemPrice("Grimy toadflax");
        int cost1 = GrandExchange.getItemPrice("Toadflax potion (unf)");
        int herb = 3049;
        int pot = 3003;
        int vial = 227;
        int quantity = 150;
        int quantity_s = (int) Bank.stream().name("Toadflax potion (unf)").count(true);
        boolean shouldbuy = true;
        boolean shouldsell = false;
        //int costs = GrandExchange.getItemPrice("Grimy toadflax")+GrandExchange.getItemPrice("Vial of water");
        //int unfValue = GrandExchange.getItemPrice("Toadflax potion (unf)");
        //int profit = unfValue-costs;
        if (Bank.open()) {
            Condition.sleep(1000);
            if (Bank.stream().name("Toadflax potion (unf)").isNotEmpty()) {
                if (Widgets.widget(NoteWidget).component(Icon).visible())
                    Widgets.widget(NoteWidget).component(Icon).click();
                if (Bank.withdraw("Toadflax potion (unf)", Bank.Amount.ALL)) {
                    System.out.println("withdrew Items");
                }
            }
            if (Bank.stream().name("Toadflax potion (unf)").isEmpty()){
                System.out.println("Closing bank");
                Bank.close();
                Condition.sleep(500);
                System.out.println("Opening Ge");
                GrandExchange.open();
                Condition.sleep(1000);
            }
            if (GrandExchange.allSlots().get(0).isAvailable()) {
                System.out.println("Buying herbs");
                GrandExchange.createOffer(herb, quantity, cost, shouldbuy);
                System.out.println("Buying Vials of water");
                GrandExchange.createOffer(vial, quantity, 6, shouldbuy);
            }
            if (Inventory.stream().name("Toadflax potion (unf)").isNotEmpty()) {
                System.out.println("Selling potions");
                GrandExchange.createOffer(pot, quantity_s, cost1, shouldsell);
            } else {
                if (GrandExchange.allSlots().get(0).isFinished()) {
                    System.out.println("Collecting our herbs");
                    GrandExchange.collectAllToBank();
                    Condition.sleep(650);
                    System.out.println("Closing Ge");
                    GrandExchange.close();
                    Condition.sleep(800);
                } else {
                   if (!GrandExchange.allSlots().get(0).isFinished()) {
                        Inventory.isEmpty();
                        System.out.println("Waiting for herbs to buy");
                        Condition.wait(() -> GrandExchange.allSlots().get(0).isFinished(), 5000, 48);
                   }
                }
            }
        }
    }
}







