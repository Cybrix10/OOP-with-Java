import javax.swing.*;
import java.util.*;

public class Main
{
    public enum State
    {
        DEFAULT,
        DEPOSITING,
        WITHDRAWING,
        CREATING
    }

    static State state = State.DEFAULT;

    static Konto curAccount;
    static int curAmount;
    static String curName;


    static int cash = 500;
    static ArrayList<Konto> accounts = new ArrayList<Konto>();

    public static void main(String[] args)
    {
        String input;

        while(true)
        {
            System.out.println("Sie haben " + cash + "€ in cash.");
            System.out.println("Geben sie einen namen für ein neues konto an: ");
            input = IO.readln();

            try{
                int inputNumber = Integer.parseInt(input);
                depositMoney(inputNumber);
            }
            catch (NumberFormatException e){
                if (input.equalsIgnoreCase("quit")) return;

                Konto acc = getKontoByName(input);

                if (acc != null){
                    withdrawMoney(acc);
                }

                if (getKontoByName(input) != null)
                {
                    System.out.println("Ein Account mit diesen Namen Exestiert bereits.");
                }
                else
                {
                    creatAcc(input);
                }
            }

            for (Konto k : accounts)
            {
                k.displayKonto();
                System.out.println();
            }
        }
    }

    static void resetState()
    {
        curAmount = 0;
        curAccount = null;
        state = State.DEFAULT;
    }

    static void withdrawMoney(Konto k){
        state = state.WITHDRAWING;
        curAccount = k;
        System.out.println("Your about to withdrawing from the following account: " );
        curAccount.displayKonto();
        System.out.println("How much do you want to withdraw?: ");
        try{
            curAmount = Integer.parseInt(IO.readln());
            cash += curAccount.withdraw(curAmount);
        }
        catch (NumberFormatException e){
            System.out.println("Withdraw enter canceled please enter a valid number! ");
        }
        resetState();
    }

    static void depositMoney(int amount){
        state = state.DEPOSITING;
        curAmount = Math.min(amount, cash);
        System.out.println("You are about to deposit " + curAmount +"€");
        System.out.println("Which account?: ");
        curAccount = getKontoByName(IO.readln());
        if(curAccount != null){
            cash -= curAmount;
            curAccount.deposit(curAmount);
        }
        else {
            System.out.println("Depositing processing canceled, please enter a valid konto name!");
        }
        resetState();
    }

    static Konto getKontoByName(String inName)
    {
        for (int i = 0; i < accounts.size(); i++)
        {
            if (accounts.get(i).getName().equalsIgnoreCase(inName))
                return accounts.get(i);
        }
        return null;
    }

    static void creatAcc(String inName){
        state = state.CREATING;
        curName = inName;
        System.out.println("What kind of account you want create?:");
        System.out.println("S for Superspar konto, T for Tageskonto and K for Girokonto");

        char type = IO.readln().toLowerCase().charAt(0);
        switch (type){
            case 'k': accounts.add(new Konto(curName)); break;
            case 's': accounts.add(new Superkonto(curName)); break;
            case 't': accounts.add(new Tageskonto(curName)); break;
            default:
                System.out.println("Creating account is canceled, pls enter a valid account type"); break;
        }
        resetState();
    }
}
