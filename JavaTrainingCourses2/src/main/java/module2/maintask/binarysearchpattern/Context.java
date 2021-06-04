package module2.maintask.binarysearchpattern;

public class Context {
    private MainClass mainClass;
    public Context(MainClass mainClass){
        this.mainClass = mainClass;
    }

    public int executeStrategy(int [] values, int searchVal){
        return this.mainClass.chooseSort(values,searchVal);
    }
}
