import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultlogger;
    Map<EventType, EventLogger> loggers;
    App(){}

    public App(Client client,  EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultlogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(Event event, EventType type, String message){

        String newMessage = message.replaceAll(client.getId(), client.getFullName());
        event.setMessage(newMessage);

        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultlogger;
        }
        logger.logEvent(event);

    }

    public static void main(String[] args) {
        /*ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");*/
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, EventType.INFO,"Some event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, EventType.INFO,"One more event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, EventType.INFO,"And one more event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, EventType.ERROR,"Some event for user 2");

        event = ctx.getBean(Event.class);
        app.logEvent(event, null,"Some event for user 3");

        ctx.close();
        /*
        ** first version without spring
        App app = new App();
        app.client = new Client("1", "John");
        app.logger = new ConsoleEventLogger();
        app.logEvent("Some event for user 1");
        */
    }
}
