package maintask;

import junit.framework.TestCase;
import module2.maintask.binarysearch.BinarySearch;
import org.junit.Test;

public class BinarySearchTreeTest {

    BinarySearch binarySearch = new BinarySearch();
    @Test
    public void insertAndSeacrhValues(){
        binarySearch.insert(40);
        binarySearch.insert(45);
        binarySearch.insert(35);
        binarySearch.insert(12);
        binarySearch.insert(37);
        TestCase.assertEquals(true, binarySearch.search(37));
    }

    @Test
    public void falseIfValusDontExists(){
        binarySearch.insert(40);
        binarySearch.insert(45);
        binarySearch.insert(35);
        binarySearch.insert(12);
        TestCase.assertEquals(false, binarySearch.search(400));
    }

    @Test
    public void validarBSInOrderTraverse(){
        binarySearch.insert(40);
        binarySearch.insert(45);
        binarySearch.insert(35);
        binarySearch.insert(12);
        binarySearch.inOrderTraverse();
    }

    @Test
    public void validarBSPostOrderTraverse(){
        binarySearch.insert(40);
        binarySearch.insert(45);
        binarySearch.insert(35);
        binarySearch.insert(12);
        binarySearch.postOrderTraverse();
    }

    @Test
    public void validarBSPreOrderTraverse(){
        binarySearch.insert(40);
        binarySearch.insert(45);
        binarySearch.insert(35);
        binarySearch.insert(12);
        binarySearch.preOrderTraverse();
    }

    @Test
    public void validarBSDelete(){
        binarySearch.insert(20);
        binarySearch.insert(12);
        binarySearch.insert(9);
        binarySearch.insert(30);
        binarySearch.insert(25);
        binarySearch.insert(35);
        binarySearch.delete(30);
        binarySearch.inOrderTraverse();
    }
}
