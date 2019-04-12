package cs4330.cs.utep.edu;

public class Walmart implements Store {

    @Override
    public String filter(String i) {
        i = i.replace("$"," ");

            String[] split = i.trim().split("\\s+");
            split[0].replace(" ","");

            i = split[0];
            return i;
    }
}
