package com.jilong.search.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jilong.search.system.SystemConfig;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author jilong.qiu
 * @date 2015/12/9.
 */
public class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(localDateTime.format(SystemConfig.DATE_TIME_FORMATTER));
    }

}
