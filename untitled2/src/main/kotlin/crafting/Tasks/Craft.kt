package crafting.Tasks

import crafting.Task
import org.powbot.api.Condition.sleep
import org.powbot.api.Condition.wait
import org.powbot.api.rt4.Inventory.selectedItem
import org.powbot.api.rt4.Inventory.stream
import org.powbot.api.rt4.Widgets.widget
import java.util.concurrent.Callable


class Craft : Task() {
    var CraftingWidget: Int = 270
    var Beer: Int = 14

    init {
        this.name = "Crafting"
    }

    fun shouldExecute(): Boolean {
        return (stream().name("Guam leaf").count() == 14L
                && stream().name("Vial of water").count() == 14L)
    }

    fun execute() {
        val itemName = "Guam leaf"
        val itemName2 = "Vial of water"
        val Guam_leaf = stream().name(itemName).first()
        val Vial_of_water = stream().name(itemName2).first()

        if (selectedItem().id() == -1) {
            Guam_leaf.interact("Use")
            sleep(650)
        } else if (selectedItem().id() == Guam_leaf.id()) {
            Vial_of_water.interact("Use")
            sleep(650)
            if (widget(CraftingWidget).component(Beer).visible()) {
                widget(CraftingWidget).component(Beer).click()
                wait(Callable { stream().name("Guam leaf").isEmpty() }, 85, 100)
            }
        }
    }
}