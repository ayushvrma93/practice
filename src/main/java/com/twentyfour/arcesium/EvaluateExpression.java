package com.twentyfour.arcesium;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


class Expression {

    private String name;
    private String expressionType;
    private String expression;
    private List<String> dependencies;
    private String result;

    public Expression(){

    }

    public Expression(String name, String expressionType,
                      String expression, List<String> dependencies){
        this.name= name;
        this.expression = expression;
        this.expressionType = expressionType;
        this.dependencies = dependencies;
        this.result = "";
    }

    public String getName(){ return this.name;}
    public String getResult(){ return this.result;}
    public String getExpression(){ return this.expression;}
    public String getExpressionType(){ return this.expressionType;}
    public List<String> getDependencies(){ return this.dependencies;}
    public void setResult(String result){this.result = result;}


}
public class EvaluateExpression {

    public static List<String> evaluate(String api) {
        // Write your code here
        String response = fetchDetailsFromAPI(api);
        JsonArray array = new Gson().fromJson(response, JsonArray.class);
        //System.out.println(array.get(0));

        Map<String, Map<String, Expression>> c = new HashMap<>();

        for(int i=0;i<array.size();i++){
            JsonObject obj = array.get(i).getAsJsonObject();
            String groupName = obj.get("groupName").getAsString();
            JsonArray arr = obj.get("expressions").getAsJsonArray();
            Map<String, Expression> tempMap = new HashMap<>();

            for(int j=0;j<arr.size();j++){
                JsonObject obj1 = arr.get(j).getAsJsonObject();
                String name = obj1.get("name").getAsString();
                String expressionType = obj1.get("expressionType").getAsString();
                String expression = obj1.get("expression").getAsString();
                JsonArray dependencies = obj1.get("dependencies").getAsJsonArray();
                List<String> dependencyList = new ArrayList<>();

                for(int k=0;k<dependencies.size();k++){
                    dependencyList.add(dependencies.get(k).toString());
                }
                //System.out.println(dependencyList);

                Expression expression2 = new Expression(name, expressionType, expression, dependencyList);
                tempMap.put(name, expression2);
            }
            c.put(groupName, tempMap);
        }
        Set<String> keySet = c.keySet();
        List<String> value = new ArrayList<>();
        for(String key: keySet){
            Map<String, Expression> group = c.get(key);
            getParsed(group);
            String finalResult = key+":";
            for(String key1: group.keySet()){
                finalResult+=key1+":"+group.get(key1).getResult()+":";
            }
            value.add(finalResult);
        }

        return value;
    }

    public static void getParsed(Map<String, Expression> dependencyMap){

        Set<String> kset = dependencyMap.keySet();

        for(String key: kset){
            getParsedUtil(key, dependencyMap.get(key), dependencyMap);
            break;
        }
        return ;
    }

    public static String getParsedUtil(String name, Expression exp, Map<String, Expression> dependencyMap){
        if(exp.getResult() != ""){return exp.getResult();}

        List<String> dependencies = exp.getDependencies();
        if(dependencies.size() == 0){
            exp.setResult(exp.getExpression());
        }
        else{
            Iterator itr = dependencies.listIterator();
            Map<String, String> resultDependencies = new HashMap<>();
            while(itr.hasNext()){
                String n = (String)(itr.next());
                String result = getParsedUtil(n, dependencyMap.get(n), dependencyMap);
                if(dependencyMap.get(n).getExpressionType()=="RS_EXPRESSION"){
                    result+=" RS";
                }else if(dependencyMap.get(n).getExpressionType()=="DOLLAR"){
                    result+=" $";
                }
                resultDependencies.put(n,result);
            }
            exp.setResult(deriveResult(exp.getExpression(), resultDependencies, exp.getExpressionType()));
        }
        return exp.getResult();
    }

    public static String deriveResult(String value, Map<String, String> dependencyValue, String expressionType){
        String result = value;
        for(String key: dependencyValue.keySet()){
            result+=" "+dependencyValue.get(key);
        }
        if(expressionType=="RS_EXPRESSION"){
            return result+" RS";
        }
        if(expressionType=="DOLLAR"){
            return result+=" $";
        }
        return result;
    }



    public static String fetchDetailsFromAPI(String api){
        try{
            URL url = new URL(api);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if(responseCode == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                return response.toString();
            }
        } catch(Exception e){
            //do nothing
        }
        return "";
    }
}
