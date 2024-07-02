package org.PlankMaker.Tasks;


import org.PlankMaker.Planker;
import org.PlankMaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;


public class Banking extends Task {
    Planker main;
    public Banking(Planker main) {
        super();
        this.name = "Banking";
        this.main = main;
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty() || !new Planking().shouldExecute();
    }
    @Override
    public void execute() {
        if (!Bank.opened()) {
            Bank.open();
        }
        if (Bank.opened()) {
            if (Inventory.stream().name("Mahogany plank").count() == 26){
                Bank.deposit("Mahogany plank", Bank.Amount.ALL);
            }
            if(Inventory.stream().name("Rune pouch").isEmpty()){
                if (Bank.withdraw("Rune pouch", 1) && Bank.withdraw("Coins", Bank.Amount.ALL)){
                    System.out.println("Withdrawing Pouch + Coins");
                    Condition.wait(() -> Inventory.stream().name("Coins").isNotEmpty(), 150 , 10);
                }
            }else {
                if (Bank.stream().name("Mahogany logs").isNotEmpty() && Inventory.stream().name("Mahogany logs").isEmpty()) {
                    System.out.println("Withdrawing logs");
                    if (Bank.withdraw("Mahogany logs", 26)) {
                        System.out.println("Logs should be in inventory");
                        if (Inventory.stream().name("Coins").count() < 100000){
                            Bank.withdraw("Coins", Bank.Amount.ALL);
                        }
                    }
                }
            }
            if (Bank.stream().name("Mahogany logs").isEmpty() && Inventory.stream().name("Mahogany logs").isEmpty()) {
                Bank.withdrawModeNoted(true);
                if (Bank.withdrawModeNoted(true)) {
                    System.out.println("Withdrew Planks");
                    Bank.withdraw("Mahogany plank", Bank.Amount.ALL);
                    main.quantity_s = (int) Inventory.stream().name("Mahogany plank").count(true);
                    Condition.wait(() ->Inventory.stream().name("Mahogany plank").isNotEmpty(),1000,10);

                }
            }
            if (Bank.close(false)) {
                System.out.println("Closing Bank");
                Bank.close();
            }
        }
    }
}
