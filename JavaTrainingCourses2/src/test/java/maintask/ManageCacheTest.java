package maintask;

import junit.framework.TestCase;
import module2.maintask.JavaBaseTask;
import module2.maintask.ManageCache;
import org.junit.Test;

public class ManageCacheTest {
    ManageCache cache = new ManageCache(100_000);

    @Test
    public void addValues(){
        //agrega los valores pero como agregar los 100_000 valores toma mas de 5 segundos
        //entonces empieza a eliminarlos por el category eviction de que si el valor
        // no es accesado en los ultimos 5 segundos que los elimine
        for (int i=0; i<106_000; i++){
            cache.put(i, i+"");
        }
    }

    @Test
    public void validateLfu() throws InterruptedException {
        for (int i=0; i<5; i++){
            cache.put(i, i+"");
        }
        Thread.sleep(3);
        JavaBaseTask newFreq = cache.get(3);
        //because index 3 was accessed it was removed from the map and inserted
        // again at the head increasing it's fraquency that indicates that the value
        // was recently accesed
        TestCase.assertEquals(2,newFreq.getFrequency());
        //was reinserted with the same value
        TestCase.assertEquals("3",newFreq.getValue());
    }
}
