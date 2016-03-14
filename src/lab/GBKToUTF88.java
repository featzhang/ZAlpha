package lab;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * 源码转换程序
 *
 * @author Administrator
 */
public class GBKToUTF88 {

    public GBKToUTF88() {
        dsdfsfds();
    }
    private ArrayList<String> files = new ArrayList<String>();

    public void findFiles(File path) {
        File[] listFiles = path.listFiles();
        for (File file : listFiles) {
            if (file.isFile()) {
                files.add(file.getPath());
            } else if (file.isDirectory()) {
                findFiles(file);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        new GBKToUTF88();
    }

    private void dsdfsfds() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int showOpenDialog = fileChooser.showOpenDialog(null);
//        fileChooser.
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getPath());
            if (selectedFile.isDirectory()) {
                findFiles(selectedFile);
            } else {
                files.add(selectedFile.getPath());
            }
            System.out.println(files.size());
            trancform();
        }
    }

    private void trancform() {
        for (String fileName : files) {
//            String s = "";
            System.out.println(fileName);
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                InputStreamReader isr = new InputStreamReader(fileInputStream);
                BufferedReader br = new BufferedReader(isr);
                fileName = fileName.replace("src", "src2");
                System.out.println("Output " + fileName);
                File file = createFile(fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bw = new BufferedWriter(osw);
                String readLine;
                while ((readLine = br.readLine()) != null) {
                    String convertString = new GbktoUtf8().convertString(readLine);
                    bw.write(convertString);
                    System.out.println(convertString);
                }

                if (!file.exists()) {
                    file.createNewFile();
                }
                bw.flush();
                bw.close();
                br.close();
            } catch (Exception ex) {
            }
        }
    }

    private static File createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            fileCheck(file);
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        return file;
    }

    private static boolean fileCheck(File file) {
        if (file == null) {
            return true;
        }
        if (file.exists()) {
            return true;
        } else {
            File parentFile = file.getParentFile();
            boolean exist = fileCheck(parentFile);
            if (!exist) {
                parentFile.mkdirs();
            }
            return false;
        }
    }
}
