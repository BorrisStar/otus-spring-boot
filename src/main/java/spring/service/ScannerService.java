package spring.service;

import java.io.IOException;
import java.util.Scanner;

public interface ScannerService {

    Scanner getScannerWithPath(String filePath) throws IOException ;

    Scanner getScannerIn() throws IOException;
}
