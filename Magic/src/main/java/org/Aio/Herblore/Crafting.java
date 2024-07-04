package org.Aio.Herblore;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Widgets;

public class Crafting extends Task {

    public Crafting() {
        super();
        this.name = "Crafting";
    }

    public int CraftingWidget = 270;
    public int Potion = 14;

    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Guam leaf").isNotEmpty() && Inventory.stream().name("Vial of water").isNotEmpty()
                || Inventory.stream().name("Harralander").isNotEmpty() && Inventory.stream().name("Vial of water").isNotEmpty()
                || Inventory.stream().name("Irit leaf").isNotEmpty() && Inventory.stream().name("Vial of water").isNotEmpty();
    }
    @Override
    public void execute() {
        String itemName = "Guam leaf";
        String itemName3 = "Harralander";
        String itemName2 = "Vial of water";
        Item Herb2 = Inventory.stream().name(itemName3).first();
        Item Herb = Inventory.itemAt(0);
        Item Vial_of_water = Inventory.stream().name(itemName2).first();
        if (Herb.valid() && Vial_of_water.valid()) {
            Herb.useOn(Vial_of_water);
            Condition.wait(() -> Widgets.widget(CraftingWidget).component(Potion).visible(), 100, 6);
        } else {
            if(Herb2.valid() && Vial_of_water.valid()) {
                Herb2.useOn(Vial_of_water);
                Condition.wait(() -> Widgets.widget(CraftingWidget).component(Potion).visible(), 100, 6);
            }
        }
        if (Widgets.widget(CraftingWidget).component(Potion).visible()) {
            Widgets.widget(CraftingWidget).component(Potion).click();
            Condition.wait(() -> Inventory.stream().name("Vial of water").isEmpty(), 100, 125);
        }
    }
}