package org.launchcode.controllers;


import org.launchcode.models.AddMenuItemForm;
import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.launchcode.models.Menu;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;


    @RequestMapping(value = "")
    public String index(Model model) {


        model.addAttribute("menuItems", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String createNewMenu(Model model){

        model.addAttribute(new Menu());
        model.addAttribute("title", "Create New Menu");

        return "menu/add";
    }

    @RequestMapping (value = "add", method = RequestMethod.POST)
    public String processAddCategoryForm(Model model, @ModelAttribute @Valid Menu menu, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create New Menu");
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }


    @RequestMapping (value = "view/{id}", method = RequestMethod.GET)
    public String viewMenu(@PathVariable int id, Model model) {
        Menu menu = menuDao.findOne(id);

        model.addAttribute("menu", menu);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheese", menu.getCheeses());
        model.addAttribute("id", id);

        return "menu/view";
    }


    @RequestMapping (value = "add-item/{id}", method = RequestMethod.GET)
    public String addItem (Model model, @PathVariable int id){

        Menu menu = menuDao.findOne(id);
        AddMenuItemForm form = new AddMenuItemForm(menu,
            cheeseDao.findAll());

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);

        return "menu/add-item";
    }

    @RequestMapping (value = "add-item", method = RequestMethod.POST)
    public String processAddItem (Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){


        if (errors.hasErrors()) {
            model.addAttribute("title", "Add item to menu: " + form.getMenu());
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);

        menuDao.save(theMenu);
        return "redirect:view/" + theMenu.getId();

    }

}
