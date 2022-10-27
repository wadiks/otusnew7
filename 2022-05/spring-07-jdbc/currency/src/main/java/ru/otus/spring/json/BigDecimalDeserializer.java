package ru.otus.spring.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.otus.spring.StringUtils;


import java.io.IOException;
import java.math.BigDecimal;


public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        var srcText = jsonParser.getText();
        if(StringUtils.isNullOrWhitespace(srcText))
            return null;

        srcText = srcText.replaceAll("[_ ]", "")
                .replace(',', '.');

        return new BigDecimal(srcText);
    }
}
