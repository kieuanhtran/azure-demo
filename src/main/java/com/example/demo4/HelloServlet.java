package com.example.demo4;

import java.io.*;
import java.util.*;

import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.StringReader;

@WebServlet(name = "helloServlet", value = "/get-data")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        ArrayList<Object> res = getData();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        res.forEach(item -> {
            LinkedHashMap<String, String> map = (LinkedHashMap)item;
            String name = map.get("name");
            out.println("<h1>" + name + "</h1>");
        });
        out.println("</body></html>");
    }

    public void destroy() {
    }

    public ArrayList<Object> getData() {
        ArrayList<Object> result = new ArrayList<>();

        //This code use async to read items by partition key
//        CosmosAsyncClient cosmosAsyncClient = new CosmosClientBuilder()
//                .endpoint("https://duycosmosaccount-eastus.documents.azure.com/")
//                .key("G6RIlzyhw9016D7u8DBRVXAgWFRtDcm2xmUlJVXFO602L9l1UCls6Zrxtoynl4krITCcnQ1vLChQACDbaUcbpw==")
//                .buildAsyncClient();
//        CosmosAsyncDatabase database = cosmosAsyncClient.getDatabase("SecondDBcreatedByJava");
//        CosmosAsyncContainer cosmosAsyncContainer = database.getContainer("DemoContainer");
//
//
//        PartitionKey partitionKey = new PartitionKey("13");
//
//        cosmosAsyncContainer.readAllItems(partitionKey, Object.class)
//                .byPage()
//                .subscribe(response -> {
//                    System.out.println("Page: " + response.getResults());
//                });

        //This code use sync to read all items without partition key
        CosmosClient client = new CosmosClientBuilder()
                .endpoint("https://duycosmosaccount-eastus.documents.azure.com/")
                .key("G6RIlzyhw9016D7u8DBRVXAgWFRtDcm2xmUlJVXFO602L9l1UCls6Zrxtoynl4krITCcnQ1vLChQACDbaUcbpw==")
                .buildClient();

        CosmosContainer container = client.getDatabase("SecondDBcreatedByJava")
                .getContainer("DemoContainer");

        String query = "SELECT * FROM DemoContainer";
        CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();

        container.queryItems(query, options, Object.class)
                .forEach(item -> {
                    System.out.println("Item: " + item);
                    result.add(item);
                });

        return result;
    }
}