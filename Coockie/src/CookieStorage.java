import javax.swing.*;
import java.math.BigInteger;

public class CookieStorage {
    private BigInteger cookies;
    private JLabel displayLabel;
    private BigInteger minimumamount = new BigInteger("0");

    public BigInteger getCookies(){
        return cookies;
    }

    void updateDisplay()
    {
        displayLabel.setText(cookies.toString());
    }

    public BigInteger modifyAmount(int amount)
    {
        cookies = cookies.add(BigInteger.valueOf(amount));
        cookies = cookies.max(minimumamount);

        updateDisplay();
        return cookies;
    }

    public CookieStorage(JLabel l)
    {
        displayLabel = l;
        cookies = minimumamount;
    }

    public CookieStorage(JLabel l, int startCookie)
    {
        displayLabel = l;
        cookies = BigInteger.valueOf(startCookie);
    }
}
