package top.chorg.support;

import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;

import static top.chorg.kernel.Variable.gson;

public class FileUtils {

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) return String.format("%.1f GB", (float) size / gb);
        else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else return String.format("%d B", size);
    }

    public static String readFileContent(String path) {
        StringBuilder content = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNextLine()) {
                content.append(sc.nextLine());
                content.append('\n');
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return content.toString();
    }

    public static <T> T readFileVar(String path, Class<T> classOfT) {
        String variable = readFileContent(path);
        return gson.fromJson(variable, classOfT);
    }

    public static boolean copyFile(String sourcePath, String targetPath) {
        try {
            File temp = new File(targetPath).getParentFile();
            if (!temp.isDirectory() && !temp.mkdirs()) return false;

            File targetFile = new File(targetPath);
            File sourceFile = new File(sourcePath);

            if (!sourceFile.isFile()) return false;

            // 新建文件输入流并对它进行缓冲
            FileInputStream input = new FileInputStream(sourceFile);
            BufferedInputStream inBuff = new BufferedInputStream(input);

            // 新建文件输出流并对它进行缓冲
            FileOutputStream output = new FileOutputStream(targetFile);
            BufferedOutputStream outBuff = new BufferedOutputStream(output);

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();

            //关闭流
            inBuff.close();
            outBuff.close();
            output.close();
            input.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    // 复制文件夹
    public static boolean copyDirectory(String sourceDir, String targetDir) {
        // 新建目标目录
        if (!(new File(targetDir)).mkdirs()) return false;
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        if (file == null) return false;
        for (File value : file) {
            if (value.isFile()) {
                // 目标文件
                File targetFile = new
                        File(new File(targetDir).getAbsolutePath()
                        + File.separator + value.getName());
                if (!copyFile(value.getPath(), targetFile.getPath())) return false;
            }
            if (value.isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + value.getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + value.getName();
                if (!copyDirectory(dir1, dir2)) return false;
            }
        }
        return true;
    }

    /**
     * 保证文件的MD5值为32位
     *
     * @param filePath 文件路径
     * @return Hash
     * @throws FileNotFoundException 文件未找到
     */
    public static String md5HashCode32(String filePath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        return md5HashCode32(fis);
    }

    /**
     * java计算文件32位md5值
     *
     * @param fis 输入流
     * @return Hash
     */
    public static String md5HashCode32(InputStream fis) {
        try {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("MD5");

            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();

            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;//解释参见最下方
                if (val < 16) {
                    /*
                     * 如果小于16，那么val值的16进制形式必然为一位，
                     * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                     * 此处高位补0。
                     */
                    hexValue.append("0");
                }
                //这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * 方法md5HashCode32 中     ((int) md5Bytes[i]) & 0xff   操作的解释：
     * 在Java语言中涉及到字节byte数组数据的一些操作时，经常看到 byte[i] & 0xff这样的操作，这里就记录总结一下这里包含的意义：
     * 1、0xff是16进制（十进制是255），它默认为整形，二进制位为32位，最低八位是“1111 1111”，其余24位都是0。
     * 2、&运算: 如果2个bit都是1，则得1，否则得0；
     * 3、byte[i] & 0xff：首先，这个操作一般都是在将byte数据转成int或者其他整形数据的过程中；使用了这个操作，最终的整形数据只有低8位有数据，其他位数都为0。
     * 4、这个操作得出的整形数据都是大于等于0并且小于等于255的
     */

}
