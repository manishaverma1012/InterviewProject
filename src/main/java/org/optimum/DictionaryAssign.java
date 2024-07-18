package org.optimum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Change {
    String path;
    Integer oldValue;
    Integer newValue;

    public Change(String path, Integer oldValue, Integer newValue) {
        this.path = path;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return String.format("{ path: '%s', old = %s, new = %s }", path, oldValue, newValue == null ? "null" : newValue);
    }
}

public class DictionaryAssign {
    public static List<Change> findChanges(Map<Integer, Integer> oldMap, Map<Integer, Integer> newMap) {
        List<Change> changes = new ArrayList<>();

        // Check for changes and additions
        for (Map.Entry<Integer, Integer> entry : newMap.entrySet()) {
            Integer key = entry.getKey();
            Integer newValue = entry.getValue();
            Integer oldValue = oldMap.get(key);

            if (oldValue == null) {
                // New addition
                changes.add(new Change("root." + key, null, newValue));
            } else if (!oldValue.equals(newValue)) {
                // Value change
                changes.add(new Change("root." + key, oldValue, newValue));
            }
        }

        // Check for deletions
        for (Map.Entry<Integer, Integer> entry : oldMap.entrySet()) {
            Integer key = entry.getKey();
            if (!newMap.containsKey(key)) {
                changes.add(new Change("root." + key, entry.getValue(), null));
            }
        }

        return changes;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> oldMap = new HashMap<>();
        oldMap.put(10, 10);
        oldMap.put(20, 10);

        Map<Integer, Integer> newMap = new HashMap<>();
        newMap.put(10, 20);
        newMap.put(20, 10);
        newMap.put(25, 30);

        List<Change> output = findChanges(oldMap, newMap);
        for (Change change : output) {
            System.out.println(change);
        }
    }
}
