package org.TestLibrary.TestScript1.Tasks;

import org.TestLibrary.TestScript1.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;

public class EatFood extends Task {

    public EatFood(){
        super();
        this.name = "EatFood";
    }


    @Override
    public boolean shouldExecute(){
        return Inventory.stream().name("Lobster").isNotEmpty() && Players.local().healthPercent()<90;
    }

    @Override
    public void execute(){
        Item food = Inventory.stream().name("Lobster").first();
        if (food.valid() && food.interact("Eat", false)){
            System.out.println("We ate some food");
            Condition.wait(()->!food.valid(),150, 10);
        } else {
            System.out.println("No food valid, or we failed the interact");
        }

    }
}
