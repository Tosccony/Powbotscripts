package org.Aio.Farming;

import org.Aio.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.InventoryChangeWaiter;
import org.powbot.api.waiter.SkillExpGainedWaiter;


public class Farming1to34 extends Task {
    public Farming1to34() {
        super();
        this.name = "Farming1to34";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Farming.realLevel()<34;
    }
    Tile Start = new Tile(2954, 3224, 0);
    Tile Phials_Location = new Tile (2950, 3213, 0);
    Tile Water_Location = new Tile (2967, 3210, 0);
    int Bnoted = 8432;
    int Bag = 8431;
    int Empty_Can = 5331;
    int Full_Can = 5340;
//8431
    @Override
    public void execute() {
        GameObject House = Objects.stream().within(5).id(15478).nearest().first();
        GameObject Portal = Objects.stream().within(5).id(4525).nearest().first();
        GameObject Plant = Objects.stream().within(5).id(15366).nearest().first();
        GameObject Plant1 = Objects.stream().within(5).id(5134).nearest().first();
        GameObject Sink = Objects.stream().within(5).id(9684).nearest().first();
        Item Baggedplant = Inventory.stream().id(Bnoted).first();
        Item Empty = Inventory.stream().id(Empty_Can).first();

        if (Inventory.stream().id(Bag).isNotEmpty() && Inventory.stream().id(Full_Can).isNotEmpty()){
            if (Inventory.stream().id(Bag).isNotEmpty() && Inventory.stream().id(Full_Can).isNotEmpty() && !Portal.inViewport() && !House.inViewport()){
                Movement.moveTo(Start);
                //Condition.wait(() -> House.distance() <10 , 150, 10);
            }
            if(House.inViewport()) {
                System.out.println("Entering our home");
                House.interact("Build mode");
                Condition.wait(() -> Portal.distance() <10 , 150, 20);
            }else {
                if (Portal.inViewport() && Plant.valid()) {
                    Game.tab(Game.Tab.INVENTORY);
                    System.out.println("Placing plant");
                    Plant.interact("Build");
                    Condition.wait(() -> Widgets.component(458,4,4).visible(), 250, 40);
                    if (Widgets.component(458,4,4).visible()) {
                        System.out.println("Interacting with widget");
                        Widgets.component(458, 4, 4).click(new SkillExpGainedWaiter(Skill.Farming));
                        Condition.wait(() -> !Plant.valid(), 150, 50);

                    }
                } else {
                    if (Plant1.inViewport()) {
                        System.out.println("Removing plant");
                        Plant1.interact("Remove");
                        Condition.wait(() -> Widgets.component(219, 1, 1).visible(), 150, 10);
                    }
                    if (Widgets.component(219, 1, 1).visible()) {
                        Condition.sleep(Random.nextInt(300,500));
                        Widgets.component(219, 1, 1).click();
                        Condition.wait(() -> !Plant1.valid(), 150 , 20);
                    }
                }
            }
        } else {
            if (Inventory.stream().id(Bag).isEmpty() || Inventory.stream().id(Full_Can).isEmpty()) {
                if (Portal.inViewport()) {
                    System.out.println("Restocking");
                    Portal.interact("Enter");
                    Condition.wait(() -> !Portal.valid(), 150, 10);
                } else {
                    if (Inventory.stream().id(Bag).isEmpty()) {
                        System.out.println("Moving to Phials");
                        Movement.moveTo(Phials_Location);
                        Condition.wait(() -> Phials_Location.distance() < 3, 150, 10);
                        Npc Phials = Npcs.stream().name("Phials").first();
                        if (Phials.inViewport()) {
                            System.out.println("Un-noting");
                            Baggedplant.useOn(Phials);
                            Condition.wait(() -> Widgets.component(219, 1, 3).visible(), 250 ,10);
                        }
                        if (Widgets.component(219, 1, 3).visible()) {
                            System.out.println("Exchanging");
                            Widgets.component(219, 1,3).click();
                            Condition.wait(() -> Inventory.stream().id(Bag).isNotEmpty(), 150, 10);
                        }
                    }
                    if (Inventory.stream().id(Full_Can).isEmpty() && Inventory.stream().id(Bag).isNotEmpty()){
                        System.out.println("Moving to Sink");
                        Movement.moveTo(Water_Location);
                        Condition.wait(() ->Water_Location.distance() < 3, 150, 50);
                        if (Sink.inViewport()) {
                            System.out.println("Filling Cans");
                            Empty.useOn(Sink);
                            Condition.wait(() -> Inventory.stream().id(Full_Can).count()>3, 350 , 150);
                        }
                    }
                }
            }
        }
    }
}
