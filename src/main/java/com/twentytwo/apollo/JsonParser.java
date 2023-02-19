package com.twentytwo.apollo;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    static class JsonObject{

        String name;
        List<JsonObject> option;

        public JsonObject(String name, List<JsonObject> option) {
            this.name = name;
            this.option = option;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<JsonObject> getOption() {
            return option;
        }

        public void setOption(List<JsonObject> option) {
            this.option = option;
        }

        @Override
        public String toString() {
            return "JsonObject{" +
                    "name='" + name + '\'' +
                    ", option=" + option +
                    '}';
        }
    }

    public List<String> getAllNames(JsonObject jsonObj){

        List<String> result = new ArrayList<>();

        result.add(jsonObj.getName());

        List<JsonObject> jsons = jsonObj.getOption();

        getAllNamesUtil(jsons, result);
        return result;
    }

    private void getAllNamesUtil(List<JsonObject> jsons, List<String> result){

        if(jsons == null) return;

        for (JsonObject json : jsons) {

            result.add(json.getName());

            List<JsonObject> currJson = json.getOption();

            getAllNamesUtil(currJson, result);

        }
    }


    public static void main(String[] args) {

        JsonParser jsonParser = new JsonParser();

        List<JsonObject> jsonObjectList1 = new ArrayList<>();

        JsonObject jsonObject1 = new JsonObject("AA", null);
        JsonObject jsonObject2 = new JsonObject("AA2", null);

        jsonObjectList1.add(jsonObject1);
        jsonObjectList1.add(jsonObject2);

        JsonObject jsonObject = new JsonObject("A", jsonObjectList1);

        System.out.println(jsonParser.getAllNames(jsonObject));

        //JsonObject jsonObject1 = new JsonObject("A", new JsonObject("AA", new JsonObject("AAA", null));

    }

}
