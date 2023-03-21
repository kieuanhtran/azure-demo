package com.example.demo4;

import java.io.*;

import com.azure.cosmos.*;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedFlux;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/get-data")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        createCosmosDB();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }

    public void createCosmosDB() {
//        CosmosAsyncClient cosmosAsyncClient = new CosmosClientBuilder()
//                .endpoint("https://duycosmosaccount-eastus.documents.azure.com/")
//                .key("G6RIlzyhw9016D7u8DBRVXAgWFRtDcm2xmUlJVXFO602L9l1UCls6Zrxtoynl4krITCcnQ1vLChQACDbaUcbpw==")
//                .buildAsyncClient();
//        CosmosAsyncDatabase database = cosmosAsyncClient.getDatabase("SecondDBcreatedByJava");
//        CosmosAsyncContainer cosmosAsyncContainer = database.getContainer("DemoContainer");
//
//
//        PartitionKey partitionKey = new PartitionKey("13");
//        //        String itemId = "123";
////        cosmosAsyncContainer.readItem(itemId, partitionKey, Object.class)
////            .subscribe(response -> {
////                System.out.println("Item: " + response.getItem());
////        });
//
//        cosmosAsyncContainer.readAllItems(partitionKey, Object.class)
//                .byPage()
//                .subscribe(response -> {
//                    System.out.println("Page: " + response.getResults());
//                });

        CosmosClient client = new CosmosClientBuilder()
                .endpoint("https://duycosmosaccount-eastus.documents.azure.com/")
                .key("G6RIlzyhw9016D7u8DBRVXAgWFRtDcm2xmUlJVXFO602L9l1UCls6Zrxtoynl4krITCcnQ1vLChQACDbaUcbpw==")
                .buildClient();
//
        CosmosContainer container = client.getDatabase("SecondDBcreatedByJava")
                .getContainer("DemoContainer");


//        container.queryItems("select value c.id from c", String.class);
//
//        String itemId = "your-item-id" + Math.random();
//        String jsonString = "{\"id\": \"" + itemId + "\", \"name\": \"your-item-name\"}";
//
//        CosmosItemResponse<Object> item = container.createItem(jsonString);

        // Create a new CosmosAsyncClient via the CosmosClientBuilder
// It only requires endpoint and key, but other useful settings are available
//        CosmosAsyncClient cosmosAsyncClient = new CosmosClientBuilder()
//                .endpoint("https://duycosmosaccount-eastus.documents.azure.com/")
//                .key("G6RIlzyhw9016D7u8DBRVXAgWFRtDcm2xmUlJVXFO602L9l1UCls6Zrxtoynl4krITCcnQ1vLChQACDbaUcbpw==")
//                .buildAsyncClient();
//
//        Mono<CosmosDatabaseResponse> databaseIns = cosmosAsyncClient.createDatabaseIfNotExists("SecondDBcreatedByJava");

                // TIP: Our APIs are Reactor Core based, so try to chain your calls
//        databaseIns.flatMap(databaseResponse -> {
//                    String databaseId = databaseResponse.getProperties().getId();
//                    return cosmosAsyncClient.getDatabase(databaseId)
//                            // Create Container
//                            .createContainerIfNotExists("SecondContainerCreatedByJava", "/id")
//                            .map(containerResponse -> cosmosAsyncClient.getDatabase(databaseId)
//                                    .getContainer(containerResponse.getProperties().getId()));
//                })
//                .subscribe(container -> System.out.printf("Created container '%s' in database '%s'.%n",
//                        container.getId(), container.getDatabase().getId()));

        //check db
//        Mono<Boolean> databaseCreated = databaseIns.map(databaseResponse -> true)
//                .onErrorResume(throwable -> Mono.just(false));
//
//        databaseCreated.subscribe(wasCreated -> {
//            if (wasCreated) {
//                addItem(cosmosAsyncClient);
//                System.out.println("Database was created successfully.");
//            } else {
//                System.out.println("An error occurred while creating the database.");
//            }
//        });


    }
    public void addItem(CosmosAsyncClient cosmosAsyncClient) {
        CosmosAsyncDatabase database = cosmosAsyncClient.getDatabase("SecondDBcreatedByJava");
        CosmosAsyncContainer cosmosAsyncContainer = database.getContainer("SecondContainerCreatedByJava");
        // Create an item
        cosmosAsyncContainer.createItem(new Passenger("Carla Davis", "SEA", "IND"));
        CosmosPagedFlux<String> flux = cosmosAsyncContainer.queryItems("select * from SecondContainerCreatedByJava", String.class);
        System.out.println("Geted item: " + flux);
//                .flatMap(response -> {
//                    System.out.println("Created item: " + response.getItem());
//                    // Read that item 👓
//                    return cosmosAsyncContainer.readItem(response.getItem().getId(),
//                            new PartitionKey(response.getItem().getId()), Passenger.class);
//                });
//                .flatMap(response -> {
//                    System.out.println("Read item: " + response.getItem());
//                    // Replace that item 🔁
//                    Passenger p = response.getItem();
//                    p.setDestination("SFO");
//                    return cosmosAsyncContainer.replaceItem(p, response.getItem().getId(),
//                            new PartitionKey(response.getItem().getId()));
//                })
//                // delete that item 💣
//                .flatMap(response -> cosmosAsyncContainer.deleteItem(response.getItem().getId(),
//                        new PartitionKey(response.getItem().getId())))
//                .block(); // Blocking for demo purposes (avoid doing this in production unless you must)
// ...
//        CosmosClient client = new CosmosClientBuilder()
//                .endpoint("https://duycosmosaccount-eastus.documents.azure.com/")
//                .key("G6RIlzyhw9016D7u8DBRVXAgWFRtDcm2xmUlJVXFO602L9l1UCls6Zrxtoynl4krITCcnQ1vLChQACDbaUcbpw==")
//                .buildClient();
//
//        CosmosContainer container = client.getDatabase("SecondDBcreatedByJava")
//                .getContainer("SecondContainerCreatedByJava");
//
//        String itemId = "your-item-id" + Math.random();
//        String jsonString = "{\"id\": \"" + itemId + "\", \"name\": \"your-item-name\"}";
//
//        CosmosItemResponse<Object> item = container.createItem(jsonString);
    }

}