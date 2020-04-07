package tqs.airQuality.converter;

import org.json.JSONObject;
import org.json.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JSONObjectConverter implements AttributeConverter<JSONObject, String> {

    @Override
    public String convertToDatabaseColumn(JSONObject jsonObject) {
        String json;
        try {
            json = jsonObject.toString();
        } catch (NullPointerException ex) {
            json = "";
        }
        return json;
    }

    @Override
    public JSONObject convertToEntityAttribute(String jsonDataAsJson) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonDataAsJson);
        } catch (Exception ex) {
            jsonObject = null;
        }
        return jsonObject;
    }
}
