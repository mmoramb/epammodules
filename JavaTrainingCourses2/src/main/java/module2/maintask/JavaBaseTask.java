package module2.maintask;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class JavaBaseTask {
    private Integer  key;
    private String value;
    JavaBaseTask next;
    JavaBaseTask previous;
    private int frequency;
    private LocalDateTime accecedTime;

    public JavaBaseTask(Integer key, String value){
        accecedTime = LocalDateTime.now();
        this.value = value;
        this.key = key;
        frequency = 1;
    }

    public void setFrequency() {
        accecedTime = LocalDateTime.now();
        this.frequency += 1;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getValue() {
        return value;
    }
    public Integer getKey() {
        return key;
    }

    public LocalDateTime getAcceedTime() {
        return accecedTime;
    }

}
