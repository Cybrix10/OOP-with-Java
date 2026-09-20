public class Superkonto extends Konto
{
    protected double multyplier = 1.2;

    public Superkonto(String inName){
        super(inName);
    }

    @Override
    public void deposit(int amount)
    {
        int newAmount = (int) Math.ceil(amount * multyplier);
        super.deposit(newAmount);
    }

    protected String getKontoType()
    {
        return "Supersparkonto";
    }
}
