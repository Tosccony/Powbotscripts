package org.Aio.Hunter;

import org.Aio.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;


public class Hunting9to19 extends Task {

    public Hunting9to19() {
        super();
        this.name = "Hunting9to19";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Hunter.realLevel() < 19;
    }
    static Tile Crimtop = new Tile (2615, 2932, 0);
    static Tile Crimbot = new Tile (2606, 2924, 0);
    public static Area area1 = new Area (Crimtop, Crimbot);
    Tile Drop = new Tile(2606, 2933, 0);
    Tile Crimson = new Tile(2611, 2929, 0);


    @Override
    public void execute() {
        String itemName = "Bird snare";
        String itemName2 = "Bones";
        String itemName3 = "Raw bird meat";
         int outsnare = 10006;
        Item Snare = Inventory.stream().name(itemName).first();
        //Item Bone = Inventory.stream().name(itemName2).first();
        //Item Meat = Inventory.stream().name(itemName3).first();
        GameObject Sactive = Objects.stream().within(area1).id(9345).nearest().first();
        GameObject Sdead = Objects.stream().within(area1).id(9344).nearest().first();
        GameObject Scaught = Objects.stream().within(area1).id(9373).nearest().first();
        GroundItem Outsnare = GroundItems.stream().id(outsnare).first();
        if (Inventory.stream().name("Bones").count()>5){
            Movement.moveTo(Drop);
            if (Drop.distance()<1){
                for (int i = 0; i < 28; i++) {
                    Item Bone = Inventory.stream().name(itemName2).first();
                    Item Meat = Inventory.stream().name(itemName3).first();
                    if (Bone.name().contains("Bone") && Meat.name().contains("Raw")) {
                        System.out.println("Dropping Bones & Meat");
                        Bone.interact("Drop");
                        Meat.interact("Drop");
                        Condition.sleep(Random.nextInt(225, 300));
                    }
                }
            }
        } else {
            if (Crimson.distance()>5) {
                Movement.moveTo(Crimson);
            } else {
                if (!Sactive.inViewport() && !Sdead.inViewport() && !Scaught.inViewport() && !Outsnare.inViewport()) {
                    Movement.moveTo(Crimson);
                    Condition.wait(() -> Players.local().distanceTo(Crimson)<1 , 150, 10);
                    Game.tab(Game.Tab.INVENTORY);
                    System.out.println("Laying Traps");
                    Snare.interact("Lay");
                    Condition.wait(() -> Players.local().animation() != 1 && Sactive.valid(), 500, 20);
                } else {
                    if (Scaught.valid()){
                        System.out.println("We got a bird");
                        Scaught.interact("Check");
                    } else {
                        if (Sdead.valid()) {
                            System.out.println("Trap broke, dismantling");
                            Sdead.interact("Dismantle");
                        } else {
                            if (Outsnare.valid()) {
                                System.out.println("Trap broke, dismantling");
                                Outsnare.interact("Take");
                            }
                        }
                    }
                }
            }
        }
    }
}