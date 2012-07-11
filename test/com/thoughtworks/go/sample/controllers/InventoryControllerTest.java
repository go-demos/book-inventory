package com.thoughtworks.go.sample.controllers;

import com.thoughtworks.go.sample.models.StockItem;
import com.thoughtworks.go.sample.persistance.InventoryRepository;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryControllerTest {

    @Test
    public void shouldShowTheIndex() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        InventoryRepository repository = mock(InventoryRepository.class);
        List<StockItem> expected = Arrays.asList(new StockItem("isbn", 1));
        when(repository.currentLedger()).thenReturn(expected);

        InventoryController controller = new InventoryController(repository);
        ModelAndView modelAndView = controller.totalInventory(request);

        assertThat((List<StockItem>) modelAndView.getModel().get("stockItems"), is(expected));
    }

    @Test
    public void shouldPopulateFlashMessageIfAvailable() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("flash")).thenReturn("Message");

        ModelAndView modelAndView = new InventoryController(mock(InventoryRepository.class)).totalInventory(request);

        assertThat((String) modelAndView.getModel().get("message"), is("Message"));
    }
}
