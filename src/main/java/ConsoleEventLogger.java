import java.util.Collection;


public class ConsoleEventLogger implements EventLogger{
    private Collection loggers;
    public void logEvent(Event event){
        System.out.println(event);
    }
}
