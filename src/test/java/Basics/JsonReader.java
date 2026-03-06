package Basics;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {


        private JsonNode jsonData;

        // Constructor to read the JSON file and store its data in a JsonNode object
        public JsonReader(String filePath) throws IOException {
            // Create an ObjectMapper instance to read the JSON file
            ObjectMapper mapper = new ObjectMapper();
            // Read the JSON file and store its data in the jsonData variable
            jsonData = mapper.readTree(new File(filePath));
        }

        // Method to retrieve the value associated with a given key from the JSON data
        public String getValue(String key) {
            return jsonData.get(key).asText();
        }


}
