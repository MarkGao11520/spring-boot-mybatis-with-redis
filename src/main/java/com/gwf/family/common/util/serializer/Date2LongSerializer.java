package com.gwf.family.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by gaowenfeng on 2017/7/16.
 * 在需要去掉时间后面的三个0的类上面加
 * @JsonSerialize(using = Date2LongSerializer.class)注解
 */
public class Date2LongSerializer extends JsonSerializer<Date>{
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
