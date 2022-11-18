package com.xu.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Locale;
import java.util.UUID;

/**
 * java代码修改系统时间
 *
 * @author littlekid
 */
@Slf4j
public class ChangeSystemTimeUtil {
    /**
     * 修改时间
     * @param dataStr_
     * @param timeStr_
     */
    private static void changeTime(String dataStr_, String timeStr_) {
        try {
            File temDir = new File("C:\\timeTemp");
            String filePath = "setDateTime.bat";
            File batFile = new File(temDir.getPath() + "/" + filePath);

            if (!temDir.exists()) {
                temDir.mkdir();
                batFile.createNewFile();
            }

            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("@echo off\n");
            bw.write("%1 mshta vbscript:CreateObject(\"Shell.Application\").ShellExecute(\"cmd.exe\",\"/c %~s0 ::\",\"\",\"runas\",1)(window.close)&&exit\n");
            bw.write("time " + dataStr_);
            bw.newLine();
            bw.write("date " + timeStr_);
            bw.close();
            fw.close();
            Process process = Runtime.getRuntime().exec(filePath);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改系统时间
     * yyyy-MM-dd HH:mm:ss
     */
    public void updateSysDateTime(String dataStr_, String timeStr_) throws Exception {
        try {
            String osName = System.getProperty("os.name");
            // Window 系统
            if (osName.matches("^(?i)Windows.*$")) {
                changeTime(dataStr_, timeStr_);
            } else if (osName.matches("^(?i)Linux.*$")) {
                // Linux 系统 格式：yyyy-MM-dd HH:mm:ss   date -s "2017-11-11 11:11:11"
                FileWriter excutefw = new FileWriter("/usr/updateSysTime.sh");
                BufferedWriter excutebw = new BufferedWriter(excutefw);
                excutebw.write("date -s \"" + dataStr_ + " " + timeStr_ + "\"\n");
                excutebw.close();
                excutefw.close();
                String cmd_date = "sh /usr/updateSysTime.sh";
                runAndResult(cmd_date);
            } else {
                log.error("操作系统无法识别");
            }
        } catch (IOException e) {
            log.error("更改系统时间异常", e);
        }
    }

    /**
     * 执行 脚本命令  关心结果
     *
     * @param cmd
     */
    public static String runAndResult(String cmd) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        boolean execFlag = true;
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String tempFileName = "./temp" + uuid + ".sh";
        try {
            String osName = System.getProperty("os.name").toUpperCase(Locale.ENGLISH);
            if (osName.matches("^(?i)LINUX.*$") || osName.contains("MAC")) {
                FileWriter excutefw = new FileWriter(tempFileName);
                BufferedWriter excutebw = new BufferedWriter(excutefw);
                excutebw.write(cmd + "\n");
                excutebw.close();
                excutefw.close();
                String command = "bash " + tempFileName;
                Process p = Runtime.getRuntime().exec(command);
                p.waitFor();
                br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(System.lineSeparator());
                    sb.append(line);
                }
                br.close();
                br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    sb.append(System.lineSeparator());
                    sb.append(line);
                    if (line.length() > 0) {
                        execFlag = false;
                    }
                }
                br.close();
                if (execFlag) {
                } else {
                    throw new RuntimeException(sb.toString());
                }
            } else {
                throw new RuntimeException("不支持的操作系统类型");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
            FileUtils.deleteQuietly(new File(tempFileName));
        }
        return sb.toString();
    }
}
