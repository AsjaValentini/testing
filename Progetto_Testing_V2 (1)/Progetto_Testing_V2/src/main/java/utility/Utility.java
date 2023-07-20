package utility;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

import java.util.ArrayList;
import java.util.Random;

public class Utility {

    String path_file = "C:\\Users\\Gabriele\\Documenti\\Progetto_Testing_V2\\src\\main\\java\\file\\versione1.xlsx";
    public static ArrayList<MyStructure> inizio (String path_file) throws Exception {

        ArrayList<MyStructure> result = new ArrayList<>();

        // Carica file Excel
        Workbook wb = new Workbook(path_file);

        // Ottieni tutti i fogli di lavoro
        WorksheetCollection collection = wb.getWorksheets();

        // Scorri tutti i fogli di lavoro
        for (int worksheetIndex = 0; worksheetIndex < collection.getCount(); worksheetIndex++) {

            // Ottieni il foglio di lavoro usando il suo indice
            Worksheet worksheet = collection.get(worksheetIndex);

            // Ottieni il numero di righe e colonne
            int rows = worksheet.getCells().getMaxDataRow();
            int cols = worksheet.getCells().getMaxDataColumn();

            // Passa attraverso le righe
            for (int i = 0; i <= rows; i++) {

                MyStructure item = new MyStructure();

                // Scorri ogni colonna nella riga selezionata
                Object lunghezza_edit = worksheet.getCells().get(i, 0).getValue();
                Object num_spazi_edit = worksheet.getCells().get(i, 1).getValue();
                Object max_spazi_consecutivi_edit = worksheet.getCells().get(i, 2).getValue();
                Object num_spazi_inizio_edit = worksheet.getCells().get(i, 3).getValue();
                Object num_spazi_fine_edit = worksheet.getCells().get(i, 4).getValue();
                Object num_caratteri_speciali_edit = worksheet.getCells().get(i, 5).getValue();
                Object k_edit = worksheet.getCells().get(i, 6).getValue();

                if (lunghezza_edit.toString().charAt(0) == '[') {
                    //DEVO FARE TRA 2 E 100
                    lunghezza_edit = random(2, 100);
                } else if (lunghezza_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 100
                    lunghezza_edit = random(100, 200);
                }

                if (num_spazi_edit.toString().charAt(0) == '[') {
                    //DEVO FARE TRA 3 E 20
                    num_spazi_edit = random(3, 20);
                } else if (num_spazi_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 20
                    num_spazi_edit = random(20, 150);
                }

                if (max_spazi_consecutivi_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 2
                    max_spazi_consecutivi_edit = random(2, 25);
                }

                if (num_spazi_inizio_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 2
                    num_spazi_inizio_edit = random(2, 25);
                }

                if (num_spazi_fine_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 2
                    num_spazi_fine_edit = random(2, 25);
                }

                if (num_caratteri_speciali_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 2
                    num_caratteri_speciali_edit = random(2, 25);
                }

                if (k_edit.toString().charAt(0) == '>') {
                    //DEVO FARE MAGGIORE DI 1
                    k_edit = random(1, 5);
                }

                int lunghezza = castObjectToInt(lunghezza_edit);
                int num_spazi = castObjectToInt(num_spazi_edit);
                int max_spazi_consecutivi = castObjectToInt(max_spazi_consecutivi_edit);
                int num_spazi_inizio = castObjectToInt(num_spazi_inizio_edit);
                int num_spazi_fine = castObjectToInt(num_spazi_fine_edit);
                int num_caratteri_speciali = castObjectToInt(num_caratteri_speciali_edit);
                int k = castObjectToInt(k_edit);

                boolean b = controllo(lunghezza, num_spazi, max_spazi_consecutivi, num_spazi_inizio, num_spazi_fine, num_caratteri_speciali);
                if(b) {
                    String s1 = caratteri_speciali(num_caratteri_speciali);
                    String s2 = caratteri_normali(lunghezza, num_spazi, max_spazi_consecutivi, num_spazi_inizio, num_spazi_fine, num_caratteri_speciali);
                    String s3 = randomStringJoin(s1,s2);
                    String s4 = spazi(s3, lunghezza, num_spazi, max_spazi_consecutivi, num_spazi_inizio, num_spazi_fine, num_caratteri_speciali);
                    String s5 = collapseSpaces(s4, k);

                    item.setPrima(s4);
                    item.setDopo(s5);
                    item.setK(k);
                    result.add(item);
                } else {
                    continue;
                }
            }
        }
        return result;
    }

    public static int castObjectToInt(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                // Gestisci il caso in cui la stringa non rappresenti un intero valido
            }
        }

        // Gestisci il caso in cui l'oggetto non possa essere convertito in int
        // o fornisci un valore di default appropriato
        return 0;
    }

    public static int random(int min, int max) {
        // Creiamo un'istanza di Random
        Random random = new Random();

        // Generiamo un numero casuale compreso tra min e max
        int randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;

    }

    public static boolean controllo(int lunghezza, int num_spazi, int max_spazi_consecutivi, int num_spazi_inizio, int num_spazi_fine, int num_caratteri_speciali) {

        if (lunghezza < num_spazi + num_caratteri_speciali) {
            return false;
        }
        if (num_spazi_inizio > max_spazi_consecutivi) {
            return false;
        }
        if (num_spazi_fine > max_spazi_consecutivi) {
            return false;
        }
        if(num_spazi < num_spazi_inizio + num_spazi_fine) {
            return false;
        }
        return true;
    }

    public static String caratteri_speciali (int num_caratteri_speciali) {
        String specialCharacters = "!@#$%^&*()_-=[]{}|\\:;\"'<>,./";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < num_caratteri_speciali; i++) {
            int randomIndex = random.nextInt(specialCharacters.length());
            char randomChar = specialCharacters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String caratteri_normali (int lunghezza, int num_spazi, int max_spazi_consecutivi, int num_spazi_inizio, int num_spazi_fine, int num_caratteri_speciali) {
        int solo_caratteri_normali = lunghezza - (num_spazi + num_caratteri_speciali);
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb_2 = new StringBuilder();
        Random random_2 = new Random();

        for (int i = 0; i < solo_caratteri_normali; i++) {
            int randomIndex = random_2.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb_2.append(randomChar);
        }
        return sb_2.toString();
    }

    public static String randomStringJoin(String string1, String string2) {
        StringBuilder sb_3 = new StringBuilder();
        Random random_3 = new Random();

        int length1 = string1.length();
        int length2 = string2.length();

        int totalLength = length1 + length2;
        int currentIndex1 = 0;
        int currentIndex2 = 0;

        for (int i = 0; i < totalLength; i++) {
            boolean appendString1 = (currentIndex2 == length2) || (currentIndex1 < length1 && random_3.nextBoolean());

            if (appendString1) {
                sb_3.append(string1.charAt(currentIndex1));
                currentIndex1++;
            } else {
                sb_3.append(string2.charAt(currentIndex2));
                currentIndex2++;
            }
        }

        return sb_3.toString();
    }

    public static String spazi (String s3, int lunghezza, int num_spazi, int max_spazi_consecutivi, int num_spazi_inizio, int num_spazi_fine, int num_caratteri_speciali) {
        //Calcolo spazi
        int spazi_dentro = num_spazi - (num_spazi_inizio + num_spazi_fine);

        //Spazi inizio
        StringBuilder spazi_inizio = new StringBuilder();
        for (int i = 0; i < num_spazi_inizio; i++) {
            spazi_inizio.append("+");
        }

        //Spazi fine
        StringBuilder spazi_fine = new StringBuilder();
        for (int i = 0; i < num_spazi_fine; i++) {
            spazi_fine.append("+");

        }

        //Spazi in mezzo
        StringBuilder s3_lavorata = new StringBuilder();

        int length = s3.length();
        int maxSpacesToInsert = Math.min(length - 1, spazi_dentro); // Limita il numero massimo di spazi da inserire al numero di posizioni disponibili
        int spacesInserted = 0;

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            s3_lavorata.append(s3.charAt(i));

            if (i < length - 1 && spacesInserted < maxSpacesToInsert) {
                if (random.nextBoolean()) {
                    s3_lavorata.append("?");
                    spacesInserted++;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(spazi_inizio);
        result.append(s3_lavorata);
        result.append(spazi_fine);

        return result.toString();
    }

    public static String collapseSpaces(String input, int k) {
        StringBuilder result = new StringBuilder();
        int spaceCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '?' || c == '+') {
                spaceCount++;
                if (spaceCount <= k) {
                    result.append(c);
                }
            } else {
                spaceCount = 0;
                result.append(c);
            }
        }

        return result.toString();
    }

}



