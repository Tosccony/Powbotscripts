package crafting

abstract class Task {
    var name: String = "Un-named"

    abstract fun shouldExecute(): Boolean

    abstract fun execute()
}