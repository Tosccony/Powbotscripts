package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.GeSlot;
import org.powbot.api.rt4.GrandExchange;
import org.powbot.api.rt4.Inventory;

import java.util.ArrayList;




import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;


import java.util.ArrayList;
    public class IncaseIfuckupGe extends Task {
        ArrayList<GeSlot> slots = GrandExchange.allSlots();

        public IncaseIfuckupGe() {
            super();
            this.name = "GE";
        }

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
            int quantity_s = (int) Inventory.stream().name("Toadflax potion (unf)").count(true);
            boolean shouldbuy = true;
            boolean shouldsell = false;
            //int costs = GrandExchange.getItemPrice("Grimy toadflax")+GrandExchange.getItemPrice("Vial of water");
            //int unfValue = GrandExchange.getItemPrice("Toadflax potion (unf)");
            //int profit = unfValue-costs;
            if (Inventory.stream().name("Toadflax potion (unf)").isEmpty()) {
                Bank.open();
                System.out.println("Checking if bank is opened");
                Condition.wait(Bank::opened, 150, 100);  //ASK ABOUT THIS <<<----------------
                if (Bank.stream().name("Toadflax potion (unf)").isNotEmpty()) {
                    Bank.depositInventory();
                    Bank.withdrawModeNoted(true);
                    if (Bank.withdraw("Toadflax potion (unf)", Bank.Amount.ALL)) {
                        System.out.println("withdrew Items");
                        //Condition.wait(() -> Inventory.stream().name("Toadflax").isNotEmpty(), 600,30);
                        Bank.close();
                        Condition.wait(() -> Bank.close(true), 150, 100);
                    }
                }
            } else {
                if (Inventory.stream().name("Toadflax potion (unf)").isNotEmpty()) {
                    System.out.println("Opening Ge");
                    GrandExchange.open();
                }
                if (GrandExchange.opened()) {
                    System.out.println("Checking if Ge is opened");
                    Condition.wait(GrandExchange::open, 500, 25);
                    slots.forEach(slot -> {
                        if (slots.get(0).isAvailable()) {
                            System.out.println("PLEASE TELL ME THERES A FUCKING SLOT");
                            if (slots.get(0).isAvailable()) {
                                System.out.println("Selling potions");
                                GrandExchange.createOffer(pot, quantity_s, cost1, shouldsell);
                                //GrandExchange.
                            }
                            if (!slots.get(0).isAvailable()) {
                                System.out.println("Buying herbs");
                                GrandExchange.createOffer(herb, quantity, cost, shouldbuy);
                            }
                            if (!slots.get(1).isAvailable()) {
                                System.out.println("Buying Vials of water");
                                GrandExchange.createOffer(vial, quantity, 6, shouldbuy);
                            }
                        }
                    });
                } else {
                    if (!GrandExchange.allSlots().isEmpty()) {
                        slots.forEach(slot -> {
                            if (slots.get(1).isFinished()) {
                                System.out.println("Collecting items");
                                GrandExchange.collectAllToBank();
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
    }

