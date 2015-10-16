import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by why on 10/16/15.
 */
public class FileWalker {

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                System.out.println( "File:" + f.getAbsoluteFile() );
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

    public static void main(String[] args) {
        FileWalker fw = new FileWalker();
        fw.walk("src/" );
    }


}
