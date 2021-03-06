package maintask;

import com.google.common.cache.LoadingCache;
import module2.maintask.GuavaCacheImplementation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GuavaCacheImplementationTest {
    @Test
    public void cacheEvictionCount(){
        GuavaCacheImplementation guava = new GuavaCacheImplementation();
        for (int i=0; i<100_700; i++){
            guava.addRecord(""+i,""+i);
        }
        assertFalse(0== guava.getCacheEvictions());
    }

    @Test
    public void averageAddingValues(){
        GuavaCacheImplementation guava = new GuavaCacheImplementation();
        for (int i=0; i<100_700; i++){
            guava.addRecord(""+i,""+i);
        }
        assertEquals(0.0d, guava.getAveragePuttingValues(),0.01);
    }

    @Test
    public void valueHasExpired(){
        GuavaCacheImplementation guava = new GuavaCacheImplementation();
        try {
            guava.addRecord("2","3");
            String val =    guava.getRecord("2");
            Thread.sleep(6000);
            assertEquals(null, guava.getRecord("2"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void MaxSizeMemory(){
        GuavaCacheImplementation guava = new GuavaCacheImplementation();
        for (int i=0; i<100_700; i++){
            guava.addRecord(""+i,""+i);
        }

        assertEquals(100_000,guava.getCache().size());
    }
}
