package crafting.Tasks

import org.Crafting.Task
import org.powbot.api.Condition.wait
import org.powbot.api.rt4.Inventory.stream
import org.powbot.api.rt4.walking.model.Skill
import java.util.concurrent.Callable


class Cleaning : Task() {
    init {
        this.name = "Cleaning"
    }

    fun shouldExecute(): Boolean {
        return stream().name("Grimy guam leaf").isNotEmpty()
    }

    fun execute() {
        val xp = Skill.Herblore.experience()
        val itemName = "Grimy guam leaf"
        val Grimy_guam_leaf = stream().name(itemName).first()
        run {
            //Condition.sleep(205);
            Grimy_guam_leaf.click()
            //Condition.sleep(200);
            Grimy_guam_leaf.nextPoint()
            wait(Callable<Boolean> { xp != Skill.Herblore.experience() }, 150, 5)
        }
    }
}