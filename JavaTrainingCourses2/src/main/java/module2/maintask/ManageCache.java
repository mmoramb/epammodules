package module2.maintask;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ManageCache {
    private Map<Integer, JavaBaseTask> items;
    private int capacity;
    private int evictions;
    private JavaBaseTask head;
    private JavaBaseTask tail;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readWrite = rwLock.readLock();
    public final Lock writeLock = rwLock.writeLock();

    public ManageCache(int capacity){
        this.capacity = capacity;
        this.items = new ConcurrentHashMap<>();
        removeExpired();
    }

    private  void removeExpired(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                readWrite.lock();
                JavaBaseTask delAux = tail;
                JavaBaseTask headAux = head;
                int size = items.size();
                for (int i=0; i < size; i++) {
                    if (LocalDateTime.now().isAfter(delAux.getAcceedTime().plusSeconds(10))){
                        if(delAux.previous == null){
                            items.remove(delAux.getKey());
                            System.out.println("element with key :"+delAux.getKey()+" it's been deleted because expired");
                            delAux=null;
                            headAux=null;
                            tail = delAux;
                            head = headAux;
                        }else if(delAux.previous != null){
                            var auxDel1 = delAux;
                            items.remove(delAux.getKey());
                            System.out.println("element with key :"+delAux.getKey()+" it's been deleted because expired");
                            delAux = delAux.previous;
                            delAux.next = null;
                            tail = delAux;
                        }
                    }else {
                        delAux = delAux.previous;
                    }
                }
                System.out.println("map size"+items.size());
                System.out.println("from tail");
                while (delAux != null){
                    System.out.println(delAux.getKey());
                    delAux = delAux.previous;
                }
                System.out.println("from head");
                while (headAux != null){
                    System.out.println(headAux.getKey());
                    headAux = headAux.next;
                }
                readWrite.unlock();
            }
        }, 11,11, TimeUnit.SECONDS);
    }

        public static void main(String[] args) throws InterruptedException {

            int capacity = 4;
            ManageCache manageCache = new ManageCache(capacity);
            for (int i=0; i<capacity; i++){
                manageCache.put(i, i+"");
            }
            Thread.sleep(8000);
            manageCache.get(1).getValue();
            System.out.println("value retraved :"+manageCache.get(2).getValue());
        }

    public JavaBaseTask get(int key){
        return chageFrequency(key);
    }

    private JavaBaseTask chageFrequency(int key){
        writeLock.lock();
        JavaBaseTask item = items.get(key);
        item.setFrequency();
        removeRepetitiveItem(item);
        //removing relations to add it again, should be after removing it from related list
        item.next = null;
        item.previous = null;
        items.remove(key);
        addNewValue(item.getKey(),null,item);
        writeLock.unlock();
        return item;
    }

    public  void put(Integer key, String value){
        writeLock.lock();
        if (items.size() < this.capacity){
            if (items.containsKey(key)){
                //the the element, increment it's frequency by 1, remove the element
                //from the map and (double related list), remove the elemen from map,
                // add the elemente with increased frequency again but at the head of
                //(double related list)
                chageFrequency(key);
            }else if (items.size() == 0){
                JavaBaseTask baseTask = new JavaBaseTask(key, value);
                head = baseTask;
                tail = baseTask;
                items.put(key, baseTask);
            }else {
                addNewValue(key, value, null);
            }
        }else {
            //if has reached the capacity of elements accepted, then deletes the
            // least frequent acceced element which are the ones from the tail of the
            //(double related list)
            removeTailElement();
            put(key, value);
        }
        writeLock.unlock();
    }
    private  void addNewValue(Integer key, String value, JavaBaseTask repeated){
        JavaBaseTask baseTask = null;
        if (repeated == null){
            baseTask = new JavaBaseTask(key, value);
        }else {
            baseTask = repeated;
        }
        items.put(key, baseTask);
        var auxH = head;
        head = baseTask;
        head.next = auxH;

        JavaBaseTask aux1 = tail;
        while (aux1.previous != null){
            aux1 = aux1.previous;
        }
        aux1.previous = baseTask;
        aux1.previous.next = aux1;
    }

    private  void removeTailElement(){
        //remove element from tail and reorder tail and head
        JavaBaseTask removed = items.remove(tail.getKey());
        System.out.println("element with index : " + removed.getKey() + "has been deleted doe to cache eviction");
        tail = tail.previous;
        var headAu = head;
        while (headAu.next != null){
            headAu = headAu.next;
        }
        headAu = headAu.previous;
        headAu.next = null;
    }

    private  void removeRepetitiveItem(JavaBaseTask item) {
        var hAux = head;
        var tAux = tail;
        if (hAux.getKey() == item.getKey()){
            hAux = hAux.next;
            hAux.previous=null;
        }else if(tAux.getKey() == item.getKey()){
            tAux = tAux.previous;
            tAux.next = null;
        }else {
            while (hAux.getKey() != item.getKey()){
                hAux = hAux.next;
            }
            while (tAux.getKey() != item.getKey()){
                tAux = tAux.previous;
            }
            hAux = hAux.previous;
            hAux.next = hAux.next.next;
            tAux.next.previous = tAux.previous;
            tAux.previous.next = tAux.next;
            tAux.previous=tAux.next;
        }
    }
}
