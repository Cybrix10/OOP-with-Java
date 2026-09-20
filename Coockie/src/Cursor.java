import java.time.*;

public final class Cursor
{
    LocalTime previous;
    LocalTime now;
    boolean active = true;
    double pastSecounds = 0.0;
    double tickRate= 3.5;
    CookieStorage storage;
    int cookiesPerClick = 1;


    public Cursor (CookieStorage s)
    {
        previous = LocalTime.now();
        storage = s;
        beginTick();
    }

    private void beginTick()
    {
        while(active)
        {
            now = LocalTime.now();
            Duration dur = Duration.between(previous, now);
            tick(dur.toMillis());
            previous = now;
        }
    }


    private void tick(long deltaMillis)
    {
        pastSecounds += (deltaMillis / 1000.0);
        if (pastSecounds >= tickRate)
        {
            storage.modifyAmount(cookiesPerClick);
        }
        pastSecounds -= tickRate;
    }
}