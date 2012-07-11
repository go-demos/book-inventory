package com.thoughtworks.go.sample.controllers;

import com.thoughtworks.go.sample.models.StockItem;
import com.thoughtworks.go.sample.persistance.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class InventoryController {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public ModelAndView totalInventory(HttpServletRequest request) {
        Map model = new HashMap();
        HttpSession session = request.getSession();
        if (session != null) {
            model.put("message", session.getAttribute("flash"));
            session.removeAttribute("flash");
        }
        model.put("stockItems", inventoryRepository.currentLedger());
        return new ModelAndView("stock/index", model);
    }

    @RequestMapping(value = "/stock/new", method = RequestMethod.GET)
    public ModelAndView newStockItem() {
        return new ModelAndView("stock/new", new HashMap());
    }

    @RequestMapping(value = "/stock/create", method = RequestMethod.POST)
    public ModelAndView createStockItem(@RequestParam("isbn") String isbn,
                                        @RequestParam("count") String count,
                                        HttpServletRequest request) {
        inventoryRepository.save(new StockItem(isbn, Integer.valueOf(count)));
        request.getSession(true).setAttribute("flash", "StockItem created successfully");
        return new ModelAndView(new RedirectView("/stock", true));
    }
}