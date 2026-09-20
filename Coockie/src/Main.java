import javax.swing.*;
import java.awt.*;

public class Main
{
    public static CookieStorage storage;
    public static Boolean modes = true;
    static Color lightMode = new Color(168, 92, 92);
    static Color darkMode = new Color(85, 44, 44);
    static Color lightModeCooki = new Color(0, 0, 0);
    static Color darkModeCooki = new Color(158, 139, 139);
    static JButton button;
    static JButton mode;
    static JFrame frame;
    static JLabel cookiCount;
    static JButton remove;

    public static void main(String[] args)
    {
        String[] company = {"KeksFabrik Deluxe", "Zuckerrausch Werke", "CookieForge 3000", "TeigTitan Industries",
                "KnusperKraftwerk", "Süßwaren-Syndikat", "BackBot Fabrik", "Golden Crumb Co.", "Dough Dominion",
                "ChocoChip Industries"};

        int index = randomInt(0, company.length - 1);
        remove = new JButton("Eat cookie");
        button = new JButton("Bake cookie");
        mode  = new JButton("Dark/Light");
        frame = new JFrame();
        cookiCount = new JLabel();
        storage = new CookieStorage(cookiCount, 0);

        frame.setSize(640, 360);
        frame.setLocation(450, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Cookie Clicker - " + storage.getCookies() + " cookies produced");

        JLabel factoryName = new JLabel();
        factoryName.setBounds(10, 10, 150, 50);
        factoryName.setText(company[index]);
        frame.add(factoryName);


        JTextField name = new JTextField();
        name.setBounds(150, 10, 150, 50);
        name.addActionListener((l)->{
            factoryName.setText(name.getText());
        });
        frame.add(name);

        cookiCount.setBounds(50, 50, 200, 50);
        button.setBounds(50, 100, 200, 50);
        mode.setBounds(400, 100, 100, 50);
        remove.setBounds(100,200,100,50);

        button.addActionListener((l) -> {
            storage.modifyAmount(1);
        });

        remove.addActionListener((l) -> {
            storage.modifyAmount(-1);
        });

        mode.addActionListener((l) -> {
            frame.getContentPane().setBackground(modes == true ? lightMode : darkMode);
            cookiCount.setForeground(modes == true ? lightModeCooki : darkModeCooki);
            mode.setText(modes == true ? "Dark mode" : "Light mode");
            modes = inWhatMode(modes);
        });

        frame.add(button);
        frame.add(cookiCount);
        frame.add(mode);
        frame.add(remove);
        frame.setVisible(true);


        Cursor c = new Cursor(storage);
    }

    public static boolean inWhatMode(boolean mode)
    {
        return mode = (mode == true ? false : true);
    }

    static int randomInt(int min, int max) {
        double random = Math.random();
        int randomInt = (int) (min + (random * (max - min)) + 0.5);
        return randomInt;
    }
}