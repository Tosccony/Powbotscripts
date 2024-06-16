package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class WalkToBank extends Task {

    public WalkToBank(){
        super();
        this.name = "WalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return Skill.Woodcutting.realLevel() == 25 && Inventory.stream().name("Bronze axe").isNotEmpty()
                || Skill.Woodcutting.realLevel() == 31 && Inventory.stream().name("Mithril axe").isNotEmpty()
                || Skill.Woodcutting.realLevel() == 41 && Inventory.stream().name("Adamant axe").isNotEmpty();
    }

    @Override
    public void execute() {
        System.out.println("Walking to the Bank");
        Movement.moveToBank();
        System.out.println("Done Movement");
        Bank.open();
    }
}
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
            if (Bank.withdraw("Toadflax potion (unf)", Bank.Amount.ALL))
                System.out.println("withdrew Items");
        }
        Bank.close();
        Condition.sleep(500);
        GrandExchange.open();
        Condition.sleep(1000);
        if (!GrandExchange.allSlots().get(0).isFinished()) {
            Inventory.isEmpty();
            Condition.wait(() -> GrandExchange.allSlots().get(0).isFinished(), 5000, 48);
        } else {
            if (GrandExchange.allSlots().get(0).isAvailable()) {
                GrandExchange.createOffer(herb, quantity, cost, shouldbuy);
                GrandExchange.createOffer(vial, quantity, 6, shouldbuy);
            }
            if (GrandExchange.allSlots().get(6).isAvailable()) {
                GrandExchange.createOffer(pot, quantity_s, cost1, shouldsell);
                Condition.sleep(750);
                GrandExchange.collectAllToBank();
                Condition.sleep(650);
                GrandExchange.close();
                Condition.sleep(800);
            }
        }
    }
}
}