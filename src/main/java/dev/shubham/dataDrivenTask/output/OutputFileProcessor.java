package dev.shubham.dataDrivenTask.output;

import dev.shubham.dataDrivenTask.service.DataProcessResponse;

import java.io.*;
import java.util.List;

public class OutputFileProcessor {

    private static final OutputFileProcessor INSTANCE = new OutputFileProcessor();

    private OutputFileProcessor(){
    }

    public static OutputFileProcessor getInstance(){
        return INSTANCE;
    }

    public void writeResponseToFile(String outputFileName, List<DataProcessResponse> dataProcessResponses) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFileName+".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (DataProcessResponse dataProcessResponse : dataProcessResponses) {
            printWriter.println(dataProcessResponse.display());
        }
        printWriter.close();
        fileWriter.close();
    }
}
