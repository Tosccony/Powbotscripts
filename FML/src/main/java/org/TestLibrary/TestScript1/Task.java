package org.TestLibrary.TestScript1;

public abstract class Task {

    public String name;

    public Task(){
        super();
        name = "Un-named";
    }
    public abstract boolean shouldExecute();

    public abstract void execute();
}
