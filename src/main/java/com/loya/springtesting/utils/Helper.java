package com.loya.springtesting.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class Helper {
    /**
     * utils class to convert an object to a Json String
     *
     * @param obj
     * @return
     */
    public static String asJSONString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static JSONObject asJSONObject(String jsonObject) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
