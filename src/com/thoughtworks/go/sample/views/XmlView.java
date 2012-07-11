package com.thoughtworks.go.sample.views;

import com.thoughtworks.xstream.XStream;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class XmlView implements View {
    private XStream xStream;

    public XmlView(XStream xStream) {
        this.xStream = xStream;
    }

    public String getContentType() {
        return "text/xml";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        xStream.toXML(model.get("stockItem"), response.getOutputStream());
    }
}
