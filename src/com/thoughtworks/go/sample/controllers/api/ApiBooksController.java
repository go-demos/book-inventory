package com.thoughtworks.go.sample.controllers.api;

import com.thoughtworks.go.sample.models.StockItem;
import com.thoughtworks.go.sample.persistance.InventoryRepository;
import com.thoughtworks.go.sample.views.XmlView;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiBooksController {

    private InventoryRepository inventoryRepository;

    @Autowired
    public ApiBooksController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public ModelAndView allBooks(HttpServletRequest request) {
        Map model = new HashMap();
        model.put("books", inventoryRepository.currentLedger());
        XStream xStream = new XStream();
        xStream.alias("book", StockItem.class);
        return new ModelAndView(new XmlView(xStream), model);
    }
}
