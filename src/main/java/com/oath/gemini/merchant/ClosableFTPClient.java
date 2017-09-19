package com.oath.gemini.merchant;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClosableFTPClient implements Closeable, AutoCloseable {
    public static String username, password, host;
    private static int connectionTimeout;
    private FTPClient ftp = new FTPClient();

    static {
        Configuration config = AppConfiguration.getConfig();

        username = config.getString("ftp.username");
        password = config.getString("ftp.password");
        host = config.getString("ftp.host");
        connectionTimeout = config.getInt("ftp.connection.timeout", 10000);
    }

    /**
     * Copy a local file to the FTP server
     * 
     * @param fromFile - a full pathname
     * @param toFile - an optional base file name if it differs from its from-file
     */
    public void copyTo(String fromFile, String toFile) throws Exception {
        ftp.setConnectTimeout(connectionTimeout);
        ftp.connect(host);

        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            log.error("not able to connect the ftp host: {}", host);
            throw new Exception("Exception in connecting to FTP Server");
        }

        ftp.login(username, password);
        ftp.setFileType(FTP.ASCII_FILE_TYPE);
        ftp.setCharset(Charset.forName("UTF-8"));
        ftp.enterLocalPassiveMode();
        ftp.setKeepAlive(true);

        File file = new File(fromFile);

        if (StringUtils.isBlank(toFile)) {
            // Store a file in the root directory
            Path path = Paths.get(file.toURI()).normalize();
            Path fileName = path.getFileName();
            toFile = fileName.toString();
        } else {
            // Create and change to the working directory. If the toFile does not start with the root "/", the
            // directories will be created in a current working directory.
            String[] dirs = toFile.split("/");
            if (dirs != null && dirs.length > 1) {
                for (int i = 0; i < dirs.length - 1; i++) {
                    String dir = dirs[i];

                    if (!ftp.changeWorkingDirectory(dir)) {
                        if (StringUtils.isBlank(dir)) {
                            ftp.makeDirectory(dir);
                            ftp.changeWorkingDirectory(dir);
                        }
                    }
                }
            }
        }

        try (InputStream input = new FileInputStream(file)) {
            if (!this.ftp.storeFile(toFile, input)) {
                log.error("Failed to ftp the file '{}'", fromFile);
                throw new Exception("Failed to ftp the file");
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (ftp != null) {
            ftp.logout();
            ftp.disconnect();
        }
    }
}
