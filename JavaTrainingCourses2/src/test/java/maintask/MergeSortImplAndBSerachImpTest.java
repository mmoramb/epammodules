package maintask;

import junit.framework.TestCase;
import module2.maintask.binarysearchpattern.Context;
import module2.maintask.binarysearchpattern.InsertSort;
import module2.maintask.binarysearchpattern.MergeSort;
import org.junit.Test;

public class MergeSortImplAndBSerachImpTest {
    int[] values = {5,3,7,6,3,6,7,34,65,75,23,767,98,23,456,878,9,23,54,76,79,23,1,34};
    //1,3,3,5,6,6,7,7,9,23,23,23,23,34,34,54,65,75,76,79,98,456,767,878,
    @Test
    public void validateInsertionSortWithBS(){
        Context context = new Context(new InsertSort());
        TestCase.assertEquals(-1, context.executeStrategy(values, 879));
    }

    @Test
    public void validateMergeSortWithBS(){
        Context context = new Context(new MergeSort());
        TestCase.assertEquals(-1, context.executeStrategy(values, 879));
    }
}
