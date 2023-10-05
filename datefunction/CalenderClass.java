public class CalenderClass {
    private final String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
    private final int[] monthsCodes = {0,3,3,6,1,4,6,2,5,0,3,5};
    public String findTheDay(int month, int day,int year){
        int monthCode = monthsCodes[month-1];
        int centuryCode = findCenturyCodeOfTheYear(year);
        int lastTwoDigits = year%100;
        int remainder = lastTwoDigits/4;
        int calculate;
        String response;
        calculate = (monthCode + centuryCode + lastTwoDigits + remainder + day) % 7;
        response = days[calculate];
        if (isLeapYear(year) && (month ==1 ||month==2)) {
            response = days[calculate-1];
        }
        return response;
    }

    private boolean isLeapYear(int year){
        return year % 400 == 0 && year % 100 == 0;
    }

    private int findCenturyCodeOfTheYear(int year){
        int firstTwoDigits = year/100;
        return (3 - (firstTwoDigits % 4)) * 2;
    }
}
