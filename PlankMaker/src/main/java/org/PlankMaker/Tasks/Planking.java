package org.PlankMaker.Tasks;

import org.PlankMaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;



public class Planking extends Task {

    public Planking() {
        super();
        this.name = "Planking";
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Mahogany logs").isNotEmpty() && Inventory.stream().name("Mahogany plank").count() <27;
    }

    @Override
    public void execute() {
        Item Pouch = Inventory.stream().name("Rune pouch").first();
        Item logs1 = Inventory.stream().name("Mahogany logs").first();
        if (Bank.opened()){
            Bank.close();
        } else {
            if (Inventory.stream().name("Mahogany logs").isNotEmpty()) {
                if(!Magic.LunarSpell.PLANK_MAKE.canCast()) {
                    System.out.println("Attempting to Cast if failed, Checking rune count");
                    Bank.open();
                    System.out.println("Opening pouch");
                    Pouch.click();
                    Random.nextInt(650,750);
                    System.out.println("Widget is visible");
                    Condition.wait(() -> Widgets.component(15,29,0).visible(), 350, 10);
                    if (Widgets.component(15,29,0).visible()) {
                        System.out.println("Checking if we can click our loadout");
                        Condition.wait(() -> Widgets.component(15,29, 0).visible(), 500, 10);
                        System.out.println("Clicking on our loadout");
                        Widgets.component(15, 29, 0).click();
                        Condition.wait(() -> Widgets.component(15,29,0).click(), 150,10);
                        System.out.println("We should now have our runes in our pouch, closing bank");
                    }
                }else
                if (Magic.LunarSpell.PLANK_MAKE.canCast()){
                    Game.tab(Game.Tab.MAGIC);
                    System.out.println("Casting Plank Make");
                    Magic.LunarSpell.PLANK_MAKE.cast();
                    Condition.wait(() -> !Inventory.open(), 50,10);
                    logs1.click(new SkillExpGainedWaiter(Skill.Magic));
                }
            }
        }
    }
}
