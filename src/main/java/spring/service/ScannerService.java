package spring.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public interface ScannerService {

    Scanner getScannerWithPath(String filePath) throws IOException ;

    Scanner getScannerIn() throws IOException;
}
