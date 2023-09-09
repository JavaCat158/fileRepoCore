import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> saveList = new ArrayList<>();      /* Список для обхода */

        String saveGp1 = "E://Games/savegames/gp1.dat";
        String saveGp2 = "E://Games/savegames/gp2.dat"; /* String для сейвов */
        String saveGp3 = "E://Games/savegames/gp3.dat";

        String zipSave = "E://Games/savegames/save.zip"; /* String для архива */

        saveList.add(saveGp1);
        saveList.add(saveGp2);  /* Заполняем список */
        saveList.add(saveGp3);

        GameProgress gp1 = new GameProgress(44,0,10,51.44);
        GameProgress gp2 = new GameProgress(51,1,20,71.22); /* три обьекта GameProgress */
        GameProgress gp3 = new GameProgress(99,2,30,10.44);

        saveGame(saveGp1, gp1);
        saveGame(saveGp2, gp2);
        saveGame(saveGp3, gp3);

        zipFiles("E://Games/savegames/save.zip", saveList);

        deleteSaves(saveList);

    }
    private static void saveGame(String path, GameProgress gameProgress) {
       try (ObjectOutputStream datFile = new ObjectOutputStream(new FileOutputStream(path))) {
           datFile.writeObject(gameProgress);
       } catch (IOException e ) {
           e.printStackTrace();
       }
    }
    public static void zipFiles(String zipFilePath, List<String> filesList) {
        byte[] buffer = new byte[1024];

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String filePath : filesList) {
                File file = new File(filePath);
                FileInputStream fis = new FileInputStream(file);

                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);

                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                fis.close();
                zos.closeEntry();
            }

            System.out.println("Files zipped successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteSaves(List<String> filesList) {
        for (String filePath : filesList) {
            File file = new File(filePath);
            if (!file.delete()) {
                System.out.println("Failed to delete file: " + filePath);
            }
        }
        System.out.println("Saves deleted successfully.");
    }
}


