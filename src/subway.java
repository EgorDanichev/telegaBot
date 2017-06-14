
public class subway {


    private String[] stationList={
            "Пушкинская",
            "Тверская",
            "Чеховская",
            "Беляево",
            "Преображенская Площадь",
            "Красносельская",
            "Бауманская",
            "Комсомольская"
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