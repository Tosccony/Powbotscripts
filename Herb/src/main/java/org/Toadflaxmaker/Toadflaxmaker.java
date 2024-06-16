package org.Toadflaxmaker;

import org.Toadflaxmaker.Tasks.*;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(name = "ToadflaxMaker 1.0.0", description = "Cleans Grimy toadflax and creates Toadflax potions until the end of time", author = "Tosccon", category = ScriptCategory.Crafting)

public class Toadflaxmaker extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("ToadflaxMaker 1.0.0", "", "127.0.0.1:5695", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();


    @Override
    public void onStart(){
        DaxWalker.blacklistTeleports(Teleport.values());
        taskList.add(new Banking());
        taskList.add(new Cleaning());
        taskList.add(new Craft());
        taskList.add(new Ge());
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
