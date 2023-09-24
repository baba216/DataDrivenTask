package dev.shubham.dataDrivenTask;

import dev.shubham.dataDrivenTask.input.InputFile;
import dev.shubham.dataDrivenTask.input.InputFileParser;
import dev.shubham.dataDrivenTask.input.TestCase;
import dev.shubham.dataDrivenTask.output.OutputFileProcessor;
import dev.shubham.dataDrivenTask.service.DataProcessResponse;
import dev.shubham.dataDrivenTask.service.DataProcessor;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try {
            InputFile inputFile = takeInput();
            DataProcessor dataProcessor = DataProcessor.getInstance();
            List<DataProcessResponse> dataProcessResponses = new LinkedList<>();
            for (TestCase testCase : inputFile.getTestCases()) {
                DataProcessResponse response = dataProcessor.process(testCase.getTestCaseNum(), testCase.getRequests(), testCase.getDataChange());
                dataProcessResponses.add(response);
            }
            OutputFileProcessor.getInstance().writeResponseToFile("output", dataProcessResponses);
        }catch (Exception exception){
            System.out.println("Exception while processing. Error Message:"+exception.getMessage());
        }
    }

    private static InputFile takeInput() throws FileNotFoundException {
        System.out.println("Enter input file path");
        Scanner scanner = new Scanner(System.in);
        String inputFilePath = scanner.next();
        InputFile inputFile = InputFileParser.parseFile(inputFilePath);
        return inputFile;
    }

}
