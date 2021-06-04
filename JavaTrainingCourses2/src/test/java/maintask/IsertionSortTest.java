package maintask;

import junit.framework.TestCase;
import module2.maintask.InsertionsSort;
import org.junit.Test;

import java.util.Arrays;

public class IsertionSortTest {
    InsertionsSort insertionsSort = new InsertionsSort();
    int []values = {14,33,27,10,35,19,42,44};
    int [] complexO1 = {3,4,5,6,7,9,13,16,18,23};
    int [] complexN2 = {99,94,92,82,80,73,71,68,62,54,51,48,41};

    @Test
    public void isInsertionSortWorking(){
        TestCase.assertEquals(44,insertionsSort.useInsertionSort(values)[values.length-1]);
        TestCase.assertEquals(10,insertionsSort.useInsertionSort(values)[0]);
    }

    @Test
    public void worstCaseScenario(){
        TestCase.assertEquals(41, insertionsSort.useInsertionSort(complexN2)[0]);
        TestCase.assertEquals(99, insertionsSort.useInsertionSort(complexN2)[complexN2.length-1]);
    }

    @Test
    public void bestCaseScenario(){
        TestCase.assertEquals(23,insertionsSort.useInsertionSort(complexO1)[complexO1.length-1]);
    }
}
