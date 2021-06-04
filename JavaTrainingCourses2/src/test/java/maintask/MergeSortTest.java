package maintask;

import module2.maintask.MergeSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {
    MergeSort mergeSort = new MergeSort();
    int[] values = {5,3,7,6,3,6,7,34,65,75,23,767,98,23,456,878,9,23,54,76,79,23,1,34};

    @Test
    public void isSortingCorrect(){
        Assert.assertEquals(878, MergeSort.margeSort(values)[values.length-1]);
        Assert.assertEquals(1, MergeSort.margeSort(values)[0]);
    }
}
