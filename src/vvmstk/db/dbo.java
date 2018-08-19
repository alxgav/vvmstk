package vvmstk.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import vvmstk.config.common;

import static com.mongodb.client.model.Filters.eq;

public class dbo {
    private common c = new common();
    private MongoDatabase database = new MongoClient(new MongoClientURI(c.CONNECT_MONGO)).getDatabase(c.dbname);

    public dbo() {
    }

    public MongoCollection<Document> getCollection(String collection){return database.getCollection(collection);}

    public void  insertData(MongoCollection<Document> collection, Document doc){
        collection.insertOne(doc);
    }
    public void updateDataID(MongoCollection<Document> collection,Object fieldData, Document document){
        UpdateResult updateResult = collection.updateOne(eq("_id", fieldData),new Document("$set",document));
        updateResult.getModifiedCount();
    }
}
