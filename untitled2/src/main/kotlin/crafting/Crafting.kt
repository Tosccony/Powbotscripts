package crafting

import crafting.Tasks.Banking
import crafting.Tasks.Cleaning
import crafting.Tasks.Craft
import crafting.Tasks.WalkToBank
import org.powbot.api.script.AbstractScript
import org.powbot.api.script.ScriptCategory
import org.powbot.api.script.ScriptManifest
import org.powbot.dax.api.DaxWalker
import org.powbot.dax.teleports.Teleport
import org.powbot.mobile.script.ScriptManager.isPaused
import org.powbot.mobile.script.ScriptManager.isStopping
import org.powbot.mobile.service.ScriptUploader

@ScriptManifest(
    name = "Crafting 1.0.0",
    description = "Aio Crafting Section of our account builder",
    author = "Tosccon",
    category = ScriptCategory.Crafting
)
class Crafting : AbstractScript() {
    var taskList: ArrayList<Task> = ArrayList<Task>()


    override fun onStart() { //WILL NEED TO EDIT THIS FOR NEW TASK LIST
        DaxWalker.blacklistTeleports(*Teleport.values())
        taskList.add(Banking())
        taskList.add(Cleaning())
        taskList.add(Craft())
        taskList.add(WalkToBank())
    }


    override fun poll() {
        for (task in taskList) {
            System.out.println("Checking task: " + task.name)
            if (task.shouldExecute()) {
                System.out.println("Running task: " + task.name)
                task.execute()
            }
            if (isStopping() || isPaused()) {
                break
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ScriptUploader().uploadAndStart("Crafting 1.0.0", "", "127.0.0.1:5695", true, false)
        }
    }
}