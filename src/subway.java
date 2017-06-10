
public class subway {


    private String[] stationList={
            "Пушкинская",
            "Тверская",
            "Московская",
            "Беляево"
    };


    public boolean isStation(String station) {

        boolean isStation = false;

        int i=0;
        while (i<stationList.length)
        {
            if (station.toLowerCase().equals(stationList[i].toLowerCase())) {
                isStation=true;
            }

            i++;
        }
        return isStation;
    }

    public void stationInfo(String name){

    }

}