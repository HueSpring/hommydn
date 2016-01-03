package dn.hommy.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.http.Part;


public class ImageBean {

    private static final String pathUser = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_system\\user.png";
    private static final String pathMale = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_system\\avatar_male.png";
    private static final String pathFemale = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_system\\avatar_female.png";
    private static final String pathManager = "D:\\ACENTER\\hommy\\web\\resources\\default\\img\\img_system\\no_image.png";

    public ImageBean() {
    }

    //use
    //change avatar
    public void changeAvatar(Part file, String addressPath, String username) throws IOException {
        deleteFile(addressPath + username + ".png");
        uploadNewFile(file, addressPath, username);
    }

    //upload image from my computer and set name for image - format image.png
    public void uploadNewFile(Part file, String addressPath, String username) throws IOException {
        InputStream input = file.getInputStream();
        Files.copy(input, new File(addressPath, file.getSubmittedFileName()).toPath());
        String image = file.getSubmittedFileName();
        renameFile(addressPath + image, addressPath + username + ".png");
    }

    //rename image - format image.png
    public void renameFile(String oldFilePath, String newFilePath) {
        File oldfile = new File(oldFilePath);
        File newfile = new File(newFilePath);
        oldfile.renameTo(newfile);
    }

    //copy file on server - manager
    public void copyFileManager(String newFilePath) throws IOException {
        File oldfile = new File(pathManager);
        File newfile = new File(newFilePath);
        Files.copy(oldfile.toPath(), newfile.toPath());
    }

    //copy file on server - member - female
    public void copyFileFemale(String newFilePath) throws IOException {
        File oldfile = new File(pathFemale);
        File newfile = new File(newFilePath);
        Files.copy(oldfile.toPath(), newfile.toPath());
    }

    //copy file on server - member - male
    public void copyFileMale(String newFilePath) throws IOException {
        File oldfile = new File(pathMale);
        File newfile = new File(newFilePath);
        Files.copy(oldfile.toPath(), newfile.toPath());
    }
    
    //copy file on server - member - no gender
    public void copyFileNoGender(String newFilePath) throws IOException {
        File oldfile = new File(pathUser);
        File newfile = new File(newFilePath);
        Files.copy(oldfile.toPath(), newfile.toPath());
    }

    //delete old image
    public void deleteFile(String filePath) {
        File currentfile = new File(filePath);
        currentfile.delete();
    }

}
