

public class place {



   String id;
   String name;
   float latitude;
   float longitude;
   String[] stationList;
   String notes;

    public place(String id, String name, float latitude, float longitude, String[] stationList, String notes) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stationList = stationList;
        this.notes = notes;
    }
}
