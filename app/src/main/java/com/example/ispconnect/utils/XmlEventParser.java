package com.example.ispconnect.utils;

import android.content.Context;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlEventParser {
    public static List<Event> parseEvents(Context context, int xmlResource) {
        List<Event> events = new ArrayList<>();
        try {
            InputStream inputStream = context.getResources().openRawResource(xmlResource);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream, null);

            Event currentEvent = null;
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("event".equals(tagName)) {
                            currentEvent = new Event();
                        } else if (currentEvent != null) {
                            switch (tagName) {
                                case "id":
                                    currentEvent.setId(Integer.parseInt(parser.nextText()));
                                    break;
                                case "title":
                                    currentEvent.setTitle(parser.nextText());
                                    break;
                                case "date":
                                    currentEvent.setDate(parser.nextText());
                                    break;
                                case "time":
                                    currentEvent.setTime(parser.nextText());
                                    break;
                                case "description":
                                    currentEvent.setDescription(parser.nextText());
                                    break;
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("event".equals(tagName) && currentEvent != null) {
                            events.add(currentEvent);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
