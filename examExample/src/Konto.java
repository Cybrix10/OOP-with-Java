public class Konto
{
    protected String name;
    protected int[] id = new int[8];
    protected int balance = 0;

    public Konto(String inName)
    {
        name = inName;
        generateRandomID();
    }

    protected String getKontoType()
    {
        return "GiroKonto";
    }

    public void deposit(int amount)
    {
        balance = balance + amount;
    }

    public int withdraw(int amount)
    {
        int withdrawlAmaount = Math.min(amount, balance);
        balance = balance - withdrawlAmaount;
        return withdrawlAmaount;
    }

    public String getName()
    {
        return name;
    }

    public void displayKonto()
    {
        System.out.print("Konto " + name + " (" + getKontoType() + ")" + ", ID: ");
        for (int i = 0; i < id.length; i++)
        {
            System.out.print(id[i]);
        }
        System.out.println();
        System.out.println("Balance: " + balance + "€");
    }

    private void generateRandomID()
    {
        for (int i = 0; i < id.length; i++)
        {
            id [i] = randomInt(0, 9);
        }
    }

    protected int randomInt (int min, int max)
    {
        double random = Math.random();
        int randomInt = (int) (min + (random * (max - min)) + 0.5);
        return randomInt;
    }
}
