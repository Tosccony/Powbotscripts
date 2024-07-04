package org.PlankMaker;

import org.PlankMaker.Tasks.Banking;
import org.PlankMaker.Tasks.Ge;
import org.PlankMaker.Tasks.Planking;
import org.powbot.api.Notifications;
import org.powbot.api.Tile;
import org.powbot.api.rt4.GrandExchange;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;


@ScriptManifest(name = "Plankmaker 1.0.0", description = "Aio Magic Section of our account builder", author = "", category = ScriptCategory.Magic)

public class Planker extends AbstractScript {

    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("PlankMaker 1.0.0", "", "127.0.0.1:5685", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();
    public int logcost =  GrandExchange.getItemPrice("Mahogany logs");
    public int plankcost = GrandExchange.getItemPrice("Mahogany plank");
    public int astralcost =  GrandExchange.getItemPrice("Astral rune");
    public int naturecost =  GrandExchange.getItemPrice("Nature rune");
    public int log = 6332;
    public int plank = 8783;
    public int astral = 9075;
    public int nature = 561;
    public int quantity = 7500;
    public int quantity_s = (int) Inventory.stream().name("Mahogany plank").count(true);
    public boolean shouldbuy = true;
    public boolean shouldsell = false;

    @Override
    public void onStart(){
        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .trackSkill(Skill.Magic)
                .trackInventoryItem(8783)
                .build();
        addPaint(paint);
        quantity_s = (int) Inventory.stream().name("Mahogany plank").count(true);
        taskList.add(new Planking());
        taskList.add(new Banking(this));
        taskList.add(new Ge(this));
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