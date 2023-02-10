package formDesigner.utils;

import java.util.Comparator;
import java.util.Map;

public class CompareList implements Comparator<Map<String,Object>> {
    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        if((int)o1.get("position")>(int) o2.get("position")) return 1;
        else return -1;
    }
}
