package org.Aio.Hunter;

import org.Aio.Task;
import org.powbot.api.*;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;


import java.util.List;



public class Hunting19to29 extends Task {


    public Hunting19to29() {
        super();
        this.name = "Hunting19to29";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Hunter.realLevel() < 29;
    }
    int trap = 9345;

    static Tile Bird1 = new Tile(2527, 2935, 0);
    static Tile Bird2 = new Tile(2526, 2936, 0);
    Area Birdone = new Area(Bird1, Bird1);
    Area Birdtwo = new Area(Bird2, Bird2);
    Tile Drop = new Tile(2527,2937, 0);
    Area Tropical = new Area(new Tile(2529, 2938, 0), (new Tile(2523, 2931, 0)));
    @Override
    public void execute() {
        int outsnare = 10006;
        GameObject Sactive1 = Objects.stream().within(5).id(9345).first();
        GameObject Sdead1 = Objects.stream().within(5).id(9344).first();
        GameObject Scaught1 = Objects.stream().within(5).id(9348).first();
        GroundItem Outsnare1 = GroundItems.stream().id(outsnare).first();
        String itemName = "Bird snare";
        Item Snare = Inventory.stream().name(itemName).first();

        if (Inventory.stream().name("Bones").count() > 5) {
            Movement.moveTo(Drop);
            Condition.wait(() -> Drop.distance() < 1, 150, 10);
            List<Item> itemsToDrop = Inventory.stream().name("Bones", "Raw bird meat").list();
            Inventory.drop(itemsToDrop);
        } else {
            //if (!Sactive1.inViewport() && !Sdead1.inViewport() && !Scaught1.inViewport() && !Outsnare1.inViewport()) {
              if (Objects.stream().at(Bird1).isEmpty()) {
                  Movement.step(Bird1);
                  Condition.wait(() -> Birdone.contains(Players.local()), 150, 10);
                  Game.tab(Game.Tab.INVENTORY);
                  System.out.println("Laying Traps");
                  Snare.interact("Lay");
                  Condition.sleep(Random.nextInt(4000,5000));
              }
              if (Objects.stream().at(Bird2).isEmpty()) {
                  Movement.step(Bird2);
                  Condition.wait(() -> Birdtwo.contains(Players.local()), 150, 10);
                  System.out.println("Laying Second Trap");
                  Snare.interact("Lay");
                  Condition.sleep(Random.nextInt(4000,5000));
              }else {
                  if (Scaught1.valid()){
                      System.out.println("We got a bird");
                      Scaught1.interact("Check");
                      Condition.wait(() -> Players.local().animation() != 1, 1000,20);
                  } else {
                      if (Sdead1.valid()) {
                          System.out.println("Trap broke, dismantling");
                          Sdead1.interact("Dismantle");
                          Condition.wait(() -> Players.local().animation() != 1, 1000,20);
                      } else {
                          if (Outsnare1.valid()) {
                              System.out.println("Trap broke, dismantling");
                              Outsnare1.interact("Take");
                              Condition.wait(() -> Players.local().animation() != 1, 1000,20);
                          }
                      }
                  }
              }
        }
    }
}


