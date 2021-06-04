package module2.maintask;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class GuavaCacheImplementation {
    Logger logger = Logger.getLogger(GuavaCacheImplementation.class.getName());

    private CacheLoader<String,String> loader= new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return key;
        }
    };

    private RemovalListener<String,String> listener = new RemovalListener<String, String>() {
        @Override
        public void onRemoval(RemovalNotification<String, String> notification) {
            if (notification.wasEvicted()){
                logger.warning(notification.getCause().name());
            }else if (notification.getCause().equals("EXPIRED")){
                logger.warning(notification.getCause().name());
            }
        }
    };

    public LoadingCache<String, String> getCache() {
        return cache;
    }

    //max zise=100_000 --
    //eviction policy --
    //time-based on last access(5 seconds) --
    //removal listener --
    //just add to log of removed entry --
    //give statistic to user --
    //average time spent for putting new values into the cache--
    //number of cache evictions --
    //support concurrency --
    private LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(100_000)//
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .recordStats()
            .removalListener(listener)
            .build(loader);

    public String getRecord(String record){
        return cache.getIfPresent(record);
    }

    public void addRecord(String key, String value){
        cache.put(key,value);
    }
    public double getAveragePuttingValues(){
        return cache.stats().averageLoadPenalty();
    }

    public Long getCacheEvictions(){
        return  cache.stats().evictionCount();
    }
}
