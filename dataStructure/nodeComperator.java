package dataStructure;

import java.util.Comparator;

public class nodeComperator implements Comparator<node> {
    @Override
    public int compare(node o1, node o2) {
        if(o1.getWeight()>o2.getWeight()){
            return 1;
        }
        if(o1.getWeight()<o2.getWeight()){
            return -1;
        }
        return 0;
    }
}
