import utility.MyStructure;
import utility.Utility;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {

        String path_file = "Progetto_Testing_V2\\src\\main\\java\\file\\versione1.xlsx";
        ArrayList<MyStructure> result = new ArrayList<>();
        result = Utility.inizio(path_file);


        for(MyStructure item : result) {
            System.out.println(item.getPrima());
            System.out.println(item.getK());
            System.out.println(item.getDopo());
            System.out.println("------------------");
        }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
