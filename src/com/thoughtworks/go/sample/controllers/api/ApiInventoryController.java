package com.thoughtworks.go.sample.controllers.api;

import com.thoughtworks.go.sample.models.StockItem;
import com.thoughtworks.go.sample.persistance.InventoryRepository;
import com.thoughtworks.go.sample.views.XmlView;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiInventoryController {

    private InventoryRepository inventoryRepository;

    @Autowired
    public ApiInventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @RequestMapping(value = "/api/stock", method = RequestMethod.GET)
    public ModelAndView allBooks(@RequestParam("isbn") String isbn) {
        Map model = new HashMap();
        StockItem value = inventoryRepository.stockFor(isbn);
        if (value == null) {
            value = new StockItem(isbn, -1);
        }
        model.put("stockItem", value);
        XStream xStream = new XStream();
        xStream.alias("stockItem", StockItem.class);
        return new ModelAndView(new XmlView(xStream), model);
    }
}
