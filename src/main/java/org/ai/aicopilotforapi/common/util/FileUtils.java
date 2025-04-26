package org.ai.aicopilotforapi.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
  public static byte[] readAll(File f) throws IOException { return Files.readAllBytes(f.toPath()); }
  public static void write(File f, byte[] data) throws IOException { Files.write(f.toPath(), data); }
}