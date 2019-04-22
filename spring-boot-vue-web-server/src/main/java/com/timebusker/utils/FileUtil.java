
package com.timebusker.utils;

import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 文件操作工具类，提供文件操作的一些方法
 */
public abstract class FileUtil {

    private static final org.apache.commons.logging.Log log = LogFactory.getLog(FileUtil.class);

    /**
     * 缓冲区大小
     */
    public static final int BUFFER_SIZE = 4096;

    /**
     * 类型：文件
     */
    public static final int FILE = 1;
    /**
     * 类型：目录
     */
    public static final int PATH = -1;
    /**
     * 类型：文件&目录
     */
    public static final int FILE_AND_PATH = 0;

    /**
     * 创建文件,如果文件的路径是当前没有的,则系统将会创建相关目录(在win下测试通过)
     *
     * @param path        文件路径
     * @param fileName    文件名(包括后缀)
     * @param existRename 定义文件存在时的处理方式，true 修改当前文件名 false 不修改
     * @return
     * @throws IOException 创建文件失败
     */
    public static File createFile(String path, String fileName, boolean existRename) throws IOException {
        createPath(path);
        String fullname = getPath(path, fileName);
        if (log.isTraceEnabled()) {
            log.trace("将创建文件：" + fullname);
        }
        File file = new File(fullname);
        if (file.exists() && existRename) {//如果文件存在，而且决定文件存在时改名的
            fileName = renameFileWhenExist(fileName);
            return createFile(path, fileName, existRename);
        } else if (!file.exists()) {//文件不存在则创建文件
            file.createNewFile();
            return file;
        }
        //其它情况直接返回
        return file;
    }

    /**
     * 创建路径
     *
     * @param path
     * @return
     */
    public static boolean createPath(String path) {
        //创建路径
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 创建路径
     *
     * @param path
     * @return
     */
    public static File createPathFile(String path) {
        //创建路径
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) return file;
        }
        return file;

    }

    /**
     * 重命名在文件名称上有冲突的文件
     *
     * @param fileName
     * @return
     */
    private static String renameFileWhenExist(String fileName) {
        String[] filenamepart = fileName.split("\\.");//处理分隔符. 有可能文件名为 aa.bb.xx.java
        String temp;
        if (filenamepart.length > 1) {
            temp = filenamepart[filenamepart.length - 2];
        } else {
            temp = fileName;
        }
        int count = 1;
        if (temp.endsWith(")")) {
            int end = temp.lastIndexOf("(");
            String num = temp.substring(end + 1, temp.length() - 1);
            try {
                count = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                count = 0;
            }
            count = count + 1;
            temp = temp.substring(0, end) + "(" + count + ")";
        } else {
            temp += "(" + count + ")";
        }
        if (filenamepart.length > 1) {
            filenamepart[filenamepart.length - 2] = temp;
            fileName = joinString(filenamepart, ".");
        } else {
            fileName = temp;
        }
        return fileName;
    }

    /**
     * 把数组按分隔符进行组装
     *
     * @param filenamepart
     * @param spliter
     * @return
     */
    private static String joinString(String[] filenamepart, String spliter) {
        StringBuffer sb = new StringBuffer(256);
        String _spliter = "";
        for (int i = 0; i < filenamepart.length; i++) {
            String part = filenamepart[i];
            sb.append(_spliter);
            sb.append(part);
            _spliter = spliter;
        }
        return sb.toString();
    }

    /**
     * 复制文件，注意使用此方法必须在外层手动关闭输入输出流
     *
     * @param ins  文件读入流
     * @param outs 文件写入流
     * @return
     * @throws IOException
     */
    public static int copyFile(InputStream ins, OutputStream outs) throws IOException {
        BufferedInputStream in = new BufferedInputStream(ins);
        BufferedOutputStream out = new BufferedOutputStream(outs);
        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }

    /**
     * 得到路径 ，最后的路径应该为 aa\bb\cc\
     *
     * @param paths
     * @return
     */
    public static String getPath(String... paths) {
        return getPath(true, paths);
    }

    /**
     * 得到路径 ，最后的路径应该为 aa\bb\cc\，也可以用于生成文件绝对路径，生成文件时请设置addSeparator为false
     *
     * @param addSeparator 是否增加最后的分隔符，如为false，它不会检测最后的字符是否为\
     * @param paths
     * @return
     */
    public static String getPath(boolean addSeparator, String... paths) {
        StringBuffer sb = new StringBuffer(256);
        for (String path : paths) {
            sb.append(path);

            if (!path.endsWith(File.separator)) {
                sb.append(File.separator);
            }
        }
        String path = sb.toString();
        if (!addSeparator) {
            //把最后的分隔符去掉
            if (path.endsWith(File.separator)) {
                path = path.substring(0, path.length() - 1);
            }
        }
        return path;
    }


    /**
     * 得到文件 ，最后的路径应该为 aa\bb\cc\xxx.java
     * 修改 by ouyh 20101124: 增加文件类型参数
     *
     * @param fileName 文件名称（不包括类型）
     * @param fileType 文件类型
     * @param paths    路径
     * @return
     */
    public static String getFile(String fileName, String fileType, String... paths) {
        StringBuffer sb = new StringBuffer(500);
        sb.append(getPath(paths));
        sb.append(fileName);
        if (fileType != null && fileType.length() > 0) {
            sb.append(".");
            sb.append(fileType);
        }
        return sb.toString();

    }

    /**
     * 取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffer(String fileName) {
        String[] filenamepart = fileName.split("\\.");//处理分隔符. 有可能文件名为 aa.bb.xx.java
        String temp;
        if (filenamepart.length > 1) {
            temp = filenamepart[filenamepart.length - 1];
        } else {
            temp = "";//无后缀名
        }
        return temp;
    }

    /**
     * 取文件名(去掉后缀名)
     *
     * @param fileName
     * @return
     */
    public static String getFilePrefix(String fileName) {
        String[] filenamepart = fileName.split("\\.");//处理分隔符. 有可能文件名为 aa.bb.xx.java
        String temp;
        if (filenamepart.length > 1) {
            StringBuffer sb = new StringBuffer();
            String spliter = "";
            for (int i = 0; i < filenamepart.length - 1; i++) {
                String path = filenamepart[i];
                sb.append(spliter);
                sb.append(path);
                spliter = ".";
            }
            return sb.toString();
        } else {
            //无后缀名
            return fileName;
        }
    }

    /**
     * 重命名文件,(a.java,b,c) -> b_a_c.java
     *
     * @param fileName
     * @param prefix   文件名前缀
     * @param suffix   文件名后缀
     * @return
     */
    public static String getTempFileName(String fileName, String prefix, String suffix) {
        String temp = getFilePrefix(fileName);
        if (prefix != null) {
            temp = prefix + "_" + temp;
        }
        if (suffix != null) {
            temp = temp + "_" + suffix;
        }
        String filetype = getFileSuffer(fileName);
        temp += "." + filetype;
        return temp;
    }

    /**
     * 获取文件系统（含共享文件目录）中的文件对象列表
     * 仅适用于适用于Windows
     *
     * @param sharePath 文件共享目录 java.lang.String
     * @return {@link List} 文件对象java.io.File集
     * @author xuedy 待完善
     */
    public static List getFilesFromWin(String sharePath) {
        List list = new ArrayList();
        File file = new File(sharePath);
        list = getWinSubFiles(file, list);
        return list;
    }

    private static List getWinSubFiles(File file, List list) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                list = getWinSubFiles(files[i], list);
            }
        } else {
            list.add(file);
        }
        return list;
    }

    public static List getFileFromURI(URI uri) {
        List list = new ArrayList();
        File file = new File(uri);
        String[] files = file.list();
        return list;
    }


    /**
     * 从给出的url中拿到文件的名称
     *
     * @param url
     * @return
     */
    public static String getFileNameFromURL(String url) {
        File file = new File(url);
        return file.getName();
    }

    /**
     * 文件路径分割符
     */
    public static String FilePathSeparator = File.separator;


    /**
     * 显示指定路径下的文件
     *
     * @param path
     * @throws FileNotFoundException
     */
    public static String[] listFiles(String path) throws FileNotFoundException {

        return listFiles(path, FileUtil.FILE, null, false);
    }


    /**
     * 列出文件
     *
     * @param path           当前目录
     * @param fileType       文件类型
     * @param confFileFilter 文件过滤器，传入要找到的文件
     * @param fullpath       返回结果是否列出全路径
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[] listFiles(String path, int fileType, String[] confFileFilter, boolean fullpath) throws FileNotFoundException {

        if (confFileFilter == null) {
            confFileFilter = new String[]{};
        }
        boolean nofilter = false;
        if (confFileFilter.length == 0) {
            nofilter = true;
        }
        List<String> targets = new ArrayList<String>(30);
        File file = new File(path);

        String subfix = (fullpath ? path : "") + FileUtil.FilePathSeparator;
        if (file.exists() && file.isDirectory()) {

            for (int i = 0; i < file.listFiles().length; i++) {
                File subfile = file.listFiles()[i];
                if (fileType == FileUtil.FILE && !subfile.isFile()) {
                    if (subfile.isDirectory()) {
                        String tempPath = path + FileUtil.FilePathSeparator + subfile.getName();
                        targets.addAll(Arrays.asList(listFiles(tempPath, fileType, confFileFilter, fullpath)));
                    }
                    continue;
                } else if (fileType == FileUtil.PATH && !subfile.isDirectory()) {
                    continue;
                }
                if (nofilter) {
                    targets.add(subfix + subfile.getName());
                } else {
                    for (int j = 0; j < confFileFilter.length; j++) {
                        String filter = confFileFilter[j];
                        if (filter.equalsIgnoreCase(subfile.getName())) {
                            targets.add(subfix + subfile.getName());
                        }
                    }
                }
            }
            return targets.toArray(new String[0]);
        } else {
            throw new FileNotFoundException("路径不存在或者不是文件夹，无法列出文件");
        }

    }

    /**
     * 列出该目录下的所有文件-包含前缀或者后缀
     *
     * @param dir    当前目录
     * @param prefix 文件前缀，为null或者""则不检查
     * @param suffix 文件后缀，为null或者""则不检查
     * @return List<File>
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<File> listFiles(String dir, String prefix, String suffix) throws FileNotFoundException {
        List<File> list = new ArrayList<>();
        File fileTarget = new File(dir);
        if (fileTarget.exists()) {
            File[] fs = fileTarget.listFiles();
            if (fs != null && fs.length > 0) {
                for (int i = 0; i < fs.length; i++) {
                    if (fs[i].isDirectory()) {
                        list.addAll(listFiles(fs[i].getPath(), prefix, suffix));
                    } else {
                        boolean prefixFlag = false, suffixFlag = false;
                        if (prefix == null || prefix.length() == 0 || fs[i].getName().startsWith(prefix.toLowerCase()) || fs[i].getName().startsWith(prefix.toUpperCase())) {
                            prefixFlag = true;
                        }
                        if (suffix == null || suffix.length() == 0 || fs[i].getName().endsWith(suffix.toLowerCase()) || fs[i].getName().endsWith(suffix.toUpperCase())) {
                            suffixFlag = true;
                        }
                        if (prefixFlag && suffixFlag) {
                            list.add(fs[i]);
                        }
                    }
                }
            }
        }
        return list;
    }


    /**
     * 查看目录下有没有符合的文件,有返回有内容的字符串
     *
     * @param path
     * @param filter
     * @return 返回数组，数组中的内容是目录文件的文件名（不带路径）
     */
    public static String[] matchFile(String path, FilenameFilter filter) {
        File file = new File(path);
        if (!file.exists() || filter == null) {
            return new String[0];
        }
        String[] matchfiles = file.list(filter);

        return matchfiles;

    }

    /**
     * 读取文件内容
     *
     * @param filepath
     * @return
     * @throws IOException
     */
    public static String getContentFromFile(String filepath) throws IOException {
        File file = new File(filepath);
        return getContentFromFile(file);

    }


    /**
     * 读取文件内容
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getContentFromFile(File file) {
        StringBuilder sb = new StringBuilder(4096);
        if (file.exists() && file.isFile()) {

            Reader reader = null;
            BufferedReader br = null;
            InputStreamReader isr = null;
            try {
                //tomcat下使用readLine()会存在中文乱码	modify by jiangyj	2011-11-11
                //reader = new FileReader(file);
                isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
                br = new BufferedReader(isr);
                String data = null;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {

                try {
                    if (isr != null) {
                        isr.close();
                    }
                } catch (IOException e) {
                    //关闭isr出错，不处理,要注意由此而引起的资源耗尽问题
                }
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    //关闭br出错，不处理,要注意由此而引起的资源耗尽问题
                }
            }
        } else {
            log.warn(String.format("文件【%s】不存在或不是文件", file.getName()));
        }
        return sb.toString();
    }


    /**
     * 往文件写内容
     *
     * @param file
     * @param content
     * @throws IOException
     */
    public static void writeFile(File file, String content) throws IOException {
        FileWriter bw = null;
        try {
            bw = (new FileWriter(file));
            bw.write(content);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    //关闭文件写入流失败，不处理
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 往文件写内容可知道文件内容的编码方式	add by jiangyj
     *
     * @param file
     * @param content
     * @throws IOException
     */
    public static void writeFile(File file, String content, String type) throws IOException {
        OutputStreamWriter bw = null;
        try {
            bw = new OutputStreamWriter(new FileOutputStream(file), type);
            bw.write(content);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    //关闭文件写入流失败，不处理
                    e.printStackTrace();
                }
            }
        }

    }


    public static Properties getPropertiesByFile(File file) {
        Properties prop = null;
        InputStream in = null;
        try {
            prop = new Properties();
            in = new FileInputStream(file);
            prop.load(in);
            prop.keySet();
        } catch (Exception e) {
            log.error("加载配置文件出错：", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return prop;
    }

    /**
     * 获取文件的字节组
     *
     * @param file
     * @return
     */
    public static byte[] getFileByte(File file) {
        FileInputStream fis = null;
        byte[] buff = null;
        try {
            fis = new FileInputStream(file);
            buff = new byte[fis.available()];
            fis.read(buff);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buff;
    }


    /**
     * 删除指定的文件
     *
     * @param strFileName 指定绝对路径的文件名
     * @return 如果删除成功true否则false
     */
    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);

        if (!fileDelete.exists() || !fileDelete.isFile()) {
            return false;
        }
        return fileDelete.delete();
    }
}