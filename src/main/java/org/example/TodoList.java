package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<String> items = new ArrayList<>();
    private final List<Boolean> completed = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(item);
                completed.add(false);
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            completed.remove(index);
            return true;
        }
        return false;
    }

    public void clear() {
        items.clear();
        completed.clear();
    }

    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            completed.set(index, true);
            return true;
        }
        return false;
    }

    public List<String> search(String query) {
        List<String> result = new ArrayList<>();

        if (query != null) {
            for (String item : items) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    result.add(item);
                }
            }
        }

        return result;
    }

    public List<String> getAll() {
        return new ArrayList<>(items);
    }

    public boolean isDone(int index) {
        return index >= 0
                && index < completed.size()
                && completed.get(index);
    }

    public int size() {
        return items.size();
    }
}