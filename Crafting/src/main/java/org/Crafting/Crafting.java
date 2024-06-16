package org.Crafting;

import org.Crafting.Tasks.Banking;
import org.Crafting.Tasks.Craft;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(name = "Crafting 1.0.0", description = "Aio Crafting Section of our account builder", author = "Tosccon", category = ScriptCategory.Crafting)

public class Crafting extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("Crafting 1.0.0", "", "127.0.0.1:5695", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();


    @Override
    public void onStart(){ //WILL NEED TO EDIT THIS FOR NEW TASK LIST
        DaxWalker.blacklistTeleports(Teleport.values());
        taskList.add(new Banking());
        taskList.add(new Craft());

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
