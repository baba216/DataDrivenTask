package dev.shubham.dataDrivenTask.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFileParser {

    public static InputFile parseFile(String path) throws FileNotFoundException {
        InputFile inputFile = new InputFile();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String testCaseNum = scanner.next();
            int tasks = scanner.nextInt();
            List<RawTaskReq> rawTaskReqs = new ArrayList<>(tasks);
            for (int i = 0; i < tasks; i++) {
                String[] inputOutput = scanner.next().split(";");
                List<Integer> taskInputs = getTaskInputs(inputOutput[0].split(","));
                List<Integer> taskOutputs = getTaskOutputs(inputOutput[1].split(","));
                rawTaskReqs.add(new RawTaskReq(i, taskInputs, taskOutputs));
            }
            List<Integer> dataChange = getDataChange(scanner.next().split(","));
            inputFile.addTestCase(new TestCase(testCaseNum, rawTaskReqs, dataChange));
        }
        scanner.close();
        return inputFile;
    }

    private static List<Integer> getDataChange(String[] dataChanges) {
        List<Integer> dataChangeTask = new ArrayList<>(dataChanges.length);
        for (int i = 0; i < dataChanges.length; i++) {
            dataChangeTask.add(Integer.parseInt(dataChanges[i]));
        }
        return dataChangeTask;
    }

    private static List<Integer> getTaskOutputs(String[] output) {
        List<Integer> outputData = new ArrayList<>(output.length);
        for (int i = 0; i < output.length; i++) {
            outputData.add(Integer.parseInt(output[i]));
        }
        return outputData;
    }

    private static List<Integer> getTaskInputs(String[] input) {
        List<Integer> inputData = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            inputData.add(Integer.parseInt(input[i]));
        }
        return inputData;
    }

}
