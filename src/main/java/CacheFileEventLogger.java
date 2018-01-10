import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;


    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }
    /*
    * write from cache on close application
    * */
    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }
    private void writeEventsFromCache() {
        /*Iterator<Event> iterator = cache.iterator();
        while (iterator.hasNext()) {
            super.logEvent(iterator.next());
        }*/
        cache.stream().forEach(super::logEvent);
    }
}
