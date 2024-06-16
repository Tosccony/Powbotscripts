package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;


public class Craft extends Task {

    public Craft(){
        super();
        this.name = "Crafting";
    }
    public int CraftingWidget = 270;
    public int Beer = 14;

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
        Item Vial_of_water  = Inventory.stream().name(itemName2).first();
        if(Bank.opened()) {
            Bank.close();
            Condition.sleep(450);
        }
        if (Inventory.selectedItem().id() == -1) {
            Herb.interact("Use");
            Condition.wait(() -> Inventory.selectedItem().valid(), 10,30);
        } else if (Inventory.selectedItem().id() == Herb.id()) {
            Vial_of_water.interact("Use");
            Condition.wait(() -> Inventory.selectedItem().valid(), 10,30);
        }
        if (Widgets.widget(CraftingWidget).component(Beer).visible()) {
            Widgets.widget(CraftingWidget).component(Beer).click();
            Condition.wait(() -> Inventory.stream().name("Toadflax").isEmpty(), 85, 100);
        }
    }

}

