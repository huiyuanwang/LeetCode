import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by why on 10/16/15.
 */
public class FileWalker {
    HashMap<File, BasicFileAttributes> hash = new HashMap<>();
    HashMap<Long, List<File>> categories = new HashMap<>();

    public void walk( String path ) throws IOException {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                hash.put(f, Files.readAttributes(f.toPath(), BasicFileAttributes.class));

                long size = Files.size(f.toPath());
                if (categories.containsKey(size)) {
                    List<File> files = categories.get(size);
                    files.add(f);
                    categories.put(size, files);
                } else {
                    List<File> files = new LinkedList<>();
                    files.add(f);
                    categories.put(size, files);
                }

                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

    public void firstCategory(HashMap<File, BasicFileAttributes> hash) {
        for (File file: hash.keySet()) {
            BasicFileAttributes attributes = hash.get(file);
            long size = attributes.size();
            if (categories.containsKey(size)) {
                List<File> files = categories.get(size);
                files.add(file);
                categories.put(size, files);
            } else {
                List<File> files = new LinkedList<>();
                files.add(file);
                categories.put(size, files);
            }
        }
    }

    boolean sameContent(Path file1, Path file2) throws IOException, NoSuchAlgorithmException {
        final long size = Files.size(file1);
        if (size != Files.size(file2))
            return false;

        if (size < 4096)
            return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));

        MessageDigest md_1 = MessageDigest.getInstance("MD5");
        MessageDigest md_2 = MessageDigest.getInstance("MD5");
        InputStream is_1 = new FileInputStream("file1.txt");
        InputStream is_2 = new FileInputStream("file2.txt");
        try {
            is_1 = new DigestInputStream(is_1, md_1);
            is_2 = new DigestInputStream(is_2, md_2);
        }
        finally {
            is_1.close();
            is_2.close();
        }
        byte[] digest_1 = md_1.digest();
        byte[] digest_2 = md_2.digest();


        try (InputStream is1 = Files.newInputStream(file1);
             InputStream is2 = Files.newInputStream(file2)) {
            // Compare byte-by-byte.
            // Note that this can be sped up drastically by reading large chunks
            // (e.g. 16 KBs) but care must be taken as InputStream.read(byte[])
            // does not neccessarily read a whole array!
            int data;
            while ((data = is1.read()) != -1)
                if (data != is2.read())
                    return false;
        }

        return true;
    }

    public static String getMD5Sum(String filePath) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");

        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
            DigestInputStream dis = new DigestInputStream(is, md);
            int read = 0;
            do{
                read = dis.read();
            }while(read > -1);
        }
        byte[] digest = md.digest();
        digest.toString();
        String result = "";

        for (int i=0; i < digest.length; i++) {
            result += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }

        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        FileWalker fw = new FileWalker();
        fw.walk("src/");
        getMD5Sum("/Users/why/IdeaProjects/LeetCode/src/Counter.java");
    }


}
