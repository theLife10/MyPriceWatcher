package cs4330.cs.utep.edu;

public class Samsclub implements Store {
    @Override
    public String filter(String i) {
        i = i.replace("current price: ","");
        i = i.replace("$"," ");

        String[] split = i.trim().split("\\s+");

        i=split[0].replace(" ","");

        return i;
    }
}
