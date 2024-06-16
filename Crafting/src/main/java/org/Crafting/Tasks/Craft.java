package org.Crafting.Tasks;

import org.Crafting.Task;
import org.powbot.api.Condition;
import org.powbot.api.event.GameActionEvent;
import org.powbot.api.event.WidgetActionEvent;
import org.powbot.api.rt4.*;
import org.powbot.proto.rt4.WidgetAction;
import org.powerbot.bot.rt4.client.internal.IMenuItem;


public class Craft extends Task {

    public Craft(){
        super();
        this.name = "Crafting";
    }
    public int CraftingWidget = 270;
    public int Beer = 14;

    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Molten glass").isNotEmpty();
    }

    @Override
    public void execute() {
        String itemName = "Glassblowing pipe";
        String itemName2 = "Molten glass";
            Item Glassblowingpipe = Inventory.stream().name(itemName).first();
            Item Moltenglass = Inventory.stream().name(itemName2).first();

            if (Inventory.selectedItem().id() == -1) {
                    Glassblowingpipe.interact("Use");
                    Condition.sleep(750);
            } else if (Inventory.selectedItem().id() == Glassblowingpipe.id()) {
                        Moltenglass.interact("Use");
                        Condition.sleep(750);

                if (Widgets.widget(CraftingWidget).component(Beer).visible()){
                    Widgets.widget(CraftingWidget).component(Beer).click();
                    Condition.wait(()->Inventory.stream().name("Molten glass").isEmpty(), 5100, 82);
            }






            }
        }

}

