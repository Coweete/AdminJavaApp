package com.essence.deserializer;

import com.essence.Model.RfidKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;

/**
 * Created by jonatan on 2016-05-04.
 */
public class RfidKeyDeserializer extends JsonDeserializer<RfidKey> {


    @Override
    public RfidKey deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        RfidKey key = new RfidKey();
        key.setId(node.get("id").asText());
        key.setEnabled(node.get("enabled").asBoolean());
        return  key;
    }
}
