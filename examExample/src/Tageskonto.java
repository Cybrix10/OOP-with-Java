public class Tageskonto extends Konto
{
    protected int limit = 50;

    public Tageskonto(String inName){
        super(inName);
    }
    @Override
    public int withdraw(int amount){
        int withdrawAmount = Math.min(limit, Math.min(amount, balance));
        balance = balance - withdrawAmount;
        limit = limit - withdrawAmount;
        return withdrawAmount;
    }

    protected String getKontoType()
    {
        return "Tageskonto";
    }
}
