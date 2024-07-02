package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;


public class Craft extends Task {

    public Craft() {
        super();
        this.name = "Crafting";
    }

    public int CraftingWidget = 270;
    public int Potion = 14;

    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Toadflax").isNotEmpty()
                && Inventory.stream().name("Vial of water").isNotEmpty();
    }
    @Override
    public void execute() {
        String itemName = "Toadflax";
        String itemName2 = "Vial of water";
        Item Herb = Inventory.stream().name(itemName).first();
        Item Vial_of_water = Inventory.stream().name(itemName2).first();
            if (Herb.valid() && Vial_of_water.valid()) {
                Herb.useOn(Vial_of_water);
                Condition.wait(() -> Widgets.widget(CraftingWidget).component(Potion).visible(), 100, 6);
            }
        if (Widgets.widget(CraftingWidget).component(Potion).visible()) {
            Widgets.widget(CraftingWidget).component(Potion).click();
            Condition.wait(() -> Inventory.stream().name("Toadflax").isEmpty(), 100, 125);
        }
        if (Inventory.stream().name("Toadflax").isEmpty()) {
            if (Inventory.stream().name("Toadflax potion(unf)").count() < 13) {
                System.out.println("Checking to see if this helps with less than 14 pots");
                Bank.open();
            }
        }
    }
}




