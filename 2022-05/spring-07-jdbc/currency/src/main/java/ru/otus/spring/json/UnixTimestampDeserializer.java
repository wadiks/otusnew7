package ru.otus.spring.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import ru.otus.spring.StringUtils;

import java.io.IOException;
import java.util.Date;


public class UnixTimestampDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String timestamp = jsonParser.getText();
        if(StringUtils.isNullOrWhitespace(timestamp))
            return null;

        int idx = timestamp.indexOf('.');
        if(idx > 0)
            timestamp = timestamp.substring(0, idx);

        try {
            return new Date(Long.parseLong(timestamp + "000"));
        }catch(NumberFormatException ex){
             //throw  new RuntimeException( String.format("Unable to deserialize timestamp: %s", timestamp));
            return null;
        }
    }
}
