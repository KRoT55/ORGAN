package goncharenko.organizer.misc;

import java.io.*;

public class FileMemory {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private File file;

    public FileMemory(String filePath) {

        this.file = new File(filePath);
    }

    public Object loadFromFile() {

        Object obj = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            //outputStream = new ObjectOutputStream(new FileOutputStream(file,true));
            obj = inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Load file error!");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Load from file");
        return obj;
    }

    public void saveToFile(Object obj) {

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(obj);
            outputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
            System.out.println("Saved to file");
        } catch (IOException e) {
            System.out.println("Save file error!");
            e.printStackTrace();
        }
    }
}
