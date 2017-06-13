import com.google.gson.*;
import org.glassfish.grizzly.utils.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;
import java.util.*;

public class city {



    public ArrayList<place> getPlacesList(String Station) throws FileNotFoundException,IndexOutOfBoundsException {

        ArrayList<place> places = new ArrayList<place>();

        for (int j=0; j<placesList().size();j++){

          String id =placesList().get(j).getAsJsonObject().get("id").getAsString();
          String name = placesList().get(j).getAsJsonObject().get("name").getAsString();
          String url = placesList().get(j).getAsJsonObject().get("url").getAsString();
          String description = placesList().get(j).getAsJsonObject().get("description").getAsString();


            if (Arrays.asList(setStationList(placesList(),j)).contains(Station)) {
                places.add( new place(id,name,url,setStationList(placesList(),j),description));
            }

        }
        return places;
}


public String[] setStationList(JsonArray jsonArray,int j){

 String [] stationList = new String[jsonArray.get(j).getAsJsonObject().get("stationList").getAsJsonArray().size() ] ;

     for (int i=0;i<jsonArray.get(j).getAsJsonObject().get("stationList").getAsJsonArray().size();i++) {
        try {
            JsonObject placeJson= jsonArray.get(j).getAsJsonObject();
            JsonArray stationListJson= placeJson.get("stationList").getAsJsonArray();
            stationList[i] = stationListJson.get(i).getAsString();
        } catch (IndexOutOfBoundsException ignored) {
        }
    }
 return stationList;
}



private JsonArray placesList() throws FileNotFoundException,IndexOutOfBoundsException{

    JsonParser parser = new JsonParser();

        JsonElement jsonElement = parser.parse( new FileReader("C:\\git\\telegaBotz\\data\\places"));

        JsonArray jsonArray = jsonElement.getAsJsonArray();

        return jsonArray;
        }

}