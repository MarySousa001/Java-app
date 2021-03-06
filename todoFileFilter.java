package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class todoFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        }
        String name = file.getName();
        String extension = Utils.getFileExtension(name);

        if(extension == null){
            return false;
        }
        else if(extension.equals("txt")){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "";
   }
}
