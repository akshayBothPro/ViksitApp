package pro.viksit.com.viksit.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Feroz on 30-03-2017.
 */

public class ImageSaver {

    private String directoryName = ".Viksit";
    private String parentDirectory = "default";
    private String fileName = "image.png";
    private Context context;
    private boolean external;

    public ImageSaver(Context context) {
        this.context = context;
    }

    public ImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageSaver setExternal(boolean external) {
        this.external = external;
        return this;
    }

    public ImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public ImageSaver setParentDirectoryName(String parentDirectory) {
        this.parentDirectory = parentDirectory;
        return this;
    }


    public void save(InputStream inputStream) {
        OutputStream fileOutputStream = null;
        try {
            fileOutputStream = new BufferedOutputStream(new FileOutputStream(createFile()));
            int bufferSize = 2048;
            byte[] buffer = new byte[bufferSize];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    public File createFile() {
        File directory;
        if (external) {
            directory = getAlbumStorageDir(directoryName);
        } else {
            directory = context.getDir(directoryName, Context.MODE_PRIVATE);
        }

        return new File(directory, fileName);
    }

    private File getAlbumStorageDir(String albumName) {
        File parent_dir = new File(Environment.getExternalStorageDirectory(), albumName);
        if (!parent_dir.mkdirs()) {

        }
        File file = new File(parent_dir, directoryName + "_" + parentDirectory);
        if (!file.mkdirs()) {
            Log.e("Talentify", "Directory " + file.getPath() + " File Name : " + fileName);
        }

        return file;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public Bitmap load() {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        // options.inPreferredConfig = Bitmap.Config.RGB_565;

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            bitmap = BitmapFactory.decodeStream(inputStream);
            System.gc();
        } catch (OutOfMemoryError oom) {
            System.err.println("File name------------------------>" + this.fileName);
            System.gc();
            bitmap = BitmapFactory.decodeStream(inputStream, null, options);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    public boolean checkFile() {
        File file = createFile();
        return file.exists();
    }

    public File pathFile() {
        File file = createFile();
        return file;
    }

    public boolean deleteFile() {
        File file = createFile();
        return file.delete();
    }

}