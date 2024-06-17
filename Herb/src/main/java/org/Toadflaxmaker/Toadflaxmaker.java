package org.Toadflaxmaker;

import org.Toadflaxmaker.Tasks.*;
import org.powbot.api.ItemCache;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(name = "ToadflaxMaker 1.0.0", description = "Cleans Grimy toadflax and creates Toadflax potions until the end of time", author = "Tosccon", category = ScriptCategory.Herblore)

public class Toadflaxmaker extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("ToadflaxMaker 1.0.0", "", "127.0.0.1:5695", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();
    public int cost =  GrandExchange.getItemPrice("Grimy toadflax"+GrandExchange.getItemPrice("Toadflax potion (unf)"));
    public int herb = 3049;
    public int pot = 3003;
    public int vial = 227;
    public int quantity = 150;
    public int quantity_s = (int) Inventory.stream().name("Toadflax potion (unf)").count(true);
    public boolean shouldbuy = true;
    public boolean shouldsell = false;



    @Override
    public void onStart(){
        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .trackSkill(Skill.Herblore)
                .build();
        addPaint(paint);
        cost = GrandExchange.getItemPrice(herb)+GrandExchange.getItemPrice(pot)+GrandExchange.getItemPrice(vial);
        quantity = 150;
        quantity_s = (int) Inventory.stream().name("Toadflax potion (unf)").count(true);
        shouldbuy = true;
        shouldsell = false;
        DaxWalker.blacklistTeleports(Teleport.values());
        taskList.add(new Banking());
        taskList.add(new Cleaning());
        taskList.add(new Craft());
        taskList.add(new Ge(this));
        //taskList.add(new WalkToBank());
    }

    @Override
    public void poll() {
        for (Task task: taskList){
            System.out.println("Checking task: "+task.name);
            if (task.shouldExecute()){
                System.out.println("Running task: "+task.name);
                task.execute();
            }
            if (ScriptManager.INSTANCE.isStopping() || ScriptManager.INSTANCE.isPaused()){
                break;
            }
        }
    }
}
