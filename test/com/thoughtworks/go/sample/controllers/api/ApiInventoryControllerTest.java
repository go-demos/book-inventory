package com.thoughtworks.go.sample.controllers.api;

import com.thoughtworks.go.sample.models.StockItem;
import com.thoughtworks.go.sample.persistance.InventoryRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiInventoryControllerTest {

    @Test
    public void testShouldReturnAnXML() throws Exception {
        InventoryRepository repository = mock(InventoryRepository.class);
        when(repository.stockFor("isbn")).thenReturn(stockItem());
        ModelAndView modelAndView = new ApiInventoryController(repository).allBooks("isbn");
        MockOutputStream outputStream = new MockOutputStream();

        modelAndView.getView().render(modelAndView.getModel(), mock(HttpServletRequest.class), response(outputStream));

        assertThat(outputStream.result(), is(file("sample_stock_item.xml")));
    }

    private HttpServletResponse response(MockOutputStream outputStream) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getOutputStream()).thenReturn(outputStream);
        return response;
    }

    private StockItem stockItem() {
        StockItem isbn = new StockItem("isbn", 20);
        isbn.setId(1);
        return isbn;
    }

    private class MockOutputStream extends ServletOutputStream {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        @Override
        public void write(int b) throws IOException {
            stream.write(b);
        }

        public String result() {
            return stream.toString();
        }
    }

    private String file(String name) throws IOException {
        return IOUtils.toString(this.getClass().getClassLoader().getResource(name).openStream());
    }

}