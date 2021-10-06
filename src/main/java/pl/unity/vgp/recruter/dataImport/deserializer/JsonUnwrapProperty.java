package pl.unity.vgp.recruter.dataImport.deserializer;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = JsonUnwrapProperty.JsonUnwrapPropertyDeserializer.class)
public @interface JsonUnwrapProperty {

    class JsonUnwrapPropertyDeserializer extends JsonDeserializer<Object>
            implements ContextualDeserializer {

        private JavaType unwrappedJavaType;
        private String unwrappedProperty;

        @Override
        public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext,
                                                    final BeanProperty beanProperty) throws JsonMappingException {
            unwrappedProperty = beanProperty.getMember().getName();
            unwrappedJavaType = beanProperty.getType();
            return this;
        }

        @Override
        public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
                throws IOException {
            System.out.println(String.format("Ignoring %s in %s/%s", unwrappedProperty,
                    jsonParser.currentName(), jsonParser.nextFieldName()));
            JsonToken token = jsonParser.nextValue();
            return jsonParser.getCodec().readValue(jsonParser, unwrappedJavaType);
        }
    }
}
