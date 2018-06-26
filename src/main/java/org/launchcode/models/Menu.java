package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Menu {
    // Initiate Variables
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Menu must have a valid name")
    private String name;

    @ManyToMany
    private List<Cheese> cheeses = new ArrayList<>();

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    // Add cheese to the list
    public void addItem(Cheese item){
        cheeses.add(item);
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    // Constructors
    public Menu(){}

    public Menu(String name){
        this.name = name;
    }
}
