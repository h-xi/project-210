package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents categories that stores ToDoItem in a list, each category can be named.

public class Categories implements Writable {
    private String categoryName;
    private ArrayList<ToDoItem> toDoItemList;


    //CONSTRUCTOR
    public Categories(String name) {
        this.categoryName = name;
        this.toDoItemList = new ArrayList<ToDoItem>();
    }

    //GETTERS
    public String getCategoryName() {
        return categoryName;
    }

    public int getListSize() {
        return toDoItemList.size();
    }

    public ArrayList<ToDoItem> getList() {
        return toDoItemList;
    }


    //EFFECTS: add ToDoItem into category
    //MODIFIES: this
    public void addToDoItemInCategory(ToDoItem tdn) {
        toDoItemList.add(tdn);
    }

    //EFFECTS: search for ToDoItem in list, if in list, return ToDoItem otherwise return null
    public ToDoItem searchForToDo(String name) {
        for (int i = 0; i < toDoItemList.size(); i++) {
            if (name.equals((toDoItemList.get(i)).getName())) {
                return toDoItemList.get(i);
            }
        }
        return null;
    }

    //EFFECTS: Searches for item, if item is in ToDoItem List, deleted ToDoItem
    //MODIFIES: this
    public void deleteToDo(String nam) {
        ToDoItem item = this.searchForToDo(nam);
        toDoItemList.remove(item);
    }


    //EFFECTS: Change name of category
    //MODIFIES: this
    public void changeName(String s) {
        this.categoryName = s;
    }

    public String getItems(ArrayList<ToDoItem> n) {
        String name = "";
        for (int i = 0; i < n.size(); i++) {
            name += "name: " + n.get(i).getName() + " ";
        }
        return name;
    }

    //EFFECTS: Returns items in Category as JSONArray
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ToDoItem t : toDoItemList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", categoryName);
        json.put("items", itemsToJson());
        return json;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
