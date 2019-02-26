import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author: dulei
 * date: 18-8-27
 * desc:
 */
public class MD5Test {
    public static void main(String[] args) {

        File file = new File("/home/dulei/Desktop/电信devices10w.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line = null;

        File outfile = new File("/home/dulei/Desktop/电信devices10w_MD5.txt");
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            fileWriter = new FileWriter(outfile);
            writer = new BufferedWriter(fileWriter);

            while ((line = bufferedReader.readLine()) != null) {
                try {
                    byte[] input = line.getBytes("UTF-8");
                    //将输入的字符串转为md5 hashcode
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    byte[] output = messageDigest.digest(input);
                    String outline = parseByte2HexStr(output);
                    writer.write(outline);
                    writer.newLine();
//                    System.out.println(outline);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
                writer.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


}
