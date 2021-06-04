package maintask;

import module2.maintask.BinarySerachRecursively;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchTest {
    private BinarySerachRecursively binarySerach = new BinarySerachRecursively();
    int [] arr = new int[300];
    Random rd;

    @Before
    public void setUp(){
        for(int i=0; i<300; i++){
            arr[i] = i;
        }
    }

    @Test
    public void binarySearchConcurrency(){
        Assert.assertEquals(3,binarySerach.binarySearch(arr, 475));
        Assert.assertEquals(3,binarySerach.recursiveBinarySearch(arr, 0,arr.length-1,475));
    }

    @Test
    public void binarySearchTimeTest(){
        //System.out.println(arr.length);
        Assert.assertEquals(299,binarySerach.binarySearch(arr, 299));
    }

    @Test
    public void binarySearchRecursiveTimeTest(){
        Assert.assertEquals(299,binarySerach.recursiveBinarySearch(arr, 0,arr.length-1,299));
    }
    //recursion looks clearer than the code put into a loop also executing both tests recursion seems to work faster than
    //iteration duo to the time of execution, testing them it looks that using recursion work a little faster than iterating
}
