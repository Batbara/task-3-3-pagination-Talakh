package by.tr.web.dao.impl;

import by.tr.web.controller.util.XMLParameter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class STAXMovieFilter implements StreamFilter {
    private int startID;
    private int endID;

    public STAXMovieFilter(int startID, int endID) {
        this.startID = startID;
        this.endID = endID;
    }

    @Override
    public boolean accept(XMLStreamReader reader) {
        int eventType = reader.getEventType();
        if (eventType == XMLStreamConstants.START_ELEMENT) {
            String elementName = reader.getLocalName();
            if (elementName.equals(XMLParameter.MOVIE)) {
                String movieID = reader.getAttributeValue(null, XMLParameter.ID);
                return checkID(movieID);
            }
        }
        return true;
    }

    private boolean checkID(String idString) {
        int id = Integer.parseInt(idString);
        return id >= startID && id < endID;
    }
}
