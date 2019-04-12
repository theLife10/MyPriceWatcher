package cs4330.cs.utep.edu;

public class Execute {
    Store store ;

    public Execute(Store s){
        store = s;
    }
    public String execute(String e){
        return store.filter(e);
    }
}
