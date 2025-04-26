package org.ai.aicopilotforapi.common.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

public class FileUtils {

  /**
   * Read all bytes from a file
   */
  public static byte[] readAll(File f) throws IOException {
    return Files.readAllBytes(f.toPath());
  }

  /**
   * Write bytes to a file
   */
  public static void write(File f, byte[] data) throws IOException {
    Files.write(f.toPath(), data);
  }

  /**
   * Save an uploaded file stream to the target directory, generating a unique filename.
   * Returns the stored filename.
   */
  public static String saveFile(InputStream input, String originalFilename, Path targetDir) throws IOException {
    if (!Files.exists(targetDir)) {
      Files.createDirectories(targetDir);
    }
    String ext = FilenameUtils.getExtension(originalFilename);
    String base = FilenameUtils.getBaseName(originalFilename);
    String filename = base + "-" + UUID.randomUUID() + (ext.isEmpty() ? "" : "." + ext);
    Path destination = targetDir.resolve(filename);
    Files.copy(input, destination, StandardCopyOption.REPLACE_EXISTING);
    return filename;
  }

  /**
   * Load a file as a Spring Resource for download.
   */
  public static Resource loadAsResource(Path filePath) throws MalformedURLException, FileNotFoundException {
    Resource resource = new UrlResource(filePath.toUri());
    if (!resource.exists() || !resource.isReadable()) {
      throw new FileNotFoundException("File not found: " + filePath.getFileName());
    }
    return resource;
  }
}
