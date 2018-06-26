package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


public class AddMenuItemForm {
    // Initialize variables
    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;


    // Getters and Setters
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    // Constructors
    public AddMenuItemForm(){}

    public  AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses){
        this.menu = menu;
        this.cheeses = cheeses;
    }

}
