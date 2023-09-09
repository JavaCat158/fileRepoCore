import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InstalationGames {
    public static void main(String[] args) throws IOException {
        List<String> nameFile = new ArrayList<>();  // список имен устанавливаемых дирректорий

        nameFile.add("E://Games/src");
        nameFile.add("E://Games/res");
        nameFile.add("E://Games/savegames");
        nameFile.add("E://Games/temp");
        nameFile.add("E://Games/src/main");
        nameFile.add("E://Games/src/test");
        nameFile.add("E://Games/res/drawables");
        nameFile.add("E://Games/res/vectors");
        nameFile.add("E://Games/res/icons");

        for (int i = 0; i < nameFile.size(); i++) {
            new File(nameFile.get(i)).mkdirs();
            if (nameFile.get(i).equals("E://Games/src/main")){
                new File("E://Games/src/main","Main.java").createNewFile();
                nameFile.add("E://Games/src/main/Main.java");
                new File("E://Games/src/main","Utils.java").createNewFile();
                nameFile.add("E://Games/src/main/Utils.java");
                
            }
            if (nameFile.get(i).equals("E://Games/temp")) {
                new File("E://Games/temp","temp.txt").createNewFile();
                nameFile.add("E://Games/temp/temp.txt");
            }
        }
        FileWriter fw = new FileWriter("E://Games/temp/temp.txt");
        for (int i = 0; i < nameFile.size(); i++) {
            fw.write(i +"." + "Успешно установлены файлы: " + nameFile.get(i) + " " + LocalDateTime.now() + '\n');
        }
        fw.flush();
        fw.close();
    }
}

