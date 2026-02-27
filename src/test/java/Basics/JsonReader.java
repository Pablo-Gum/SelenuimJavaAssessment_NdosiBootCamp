package Basics;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {


        private JsonNode jsonData;

        public JsonReader(String filePath) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            jsonData = mapper.readTree(new File(filePath));
        }

        public String getValue(String key) {
            return jsonData.get(key).asText();
        }


}
