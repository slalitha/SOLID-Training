package com.training.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.training.student.Student;

public class FileOperations {
	private BufferedReader fileReader;
	private BufferedWriter fileWriter;
	private CheckInput check;
	private StudentResultGenerator resultGenerator;

	public FileOperations() {
		File inputFile = new File("C:\\Users\\amallam\\Desktop\\Marks.txt");
		File outputFile = new File("C:\\Users\\amallam\\Desktop\\Result.txt");

		try {
			fileReader = new BufferedReader(new FileReader(inputFile));
			fileWriter = new BufferedWriter(new FileWriter(outputFile));

			check = new CheckInput();
			resultGenerator = new StudentResultGenerator();

			readFile();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readFile() {
		String line;
		try {
			while ((line = fileReader.readLine()) != null) {
				String[] info = line.split(" ");

				if (info.length < 2) {
					writeInvalid();
					continue;
				}

				if (check.checkRollNo(info[0]) && check.checkSubjectMarks(info)) {
					Student student = resultGenerator.generateStudentResult(info);
					writeResult(student);
				} else {
					writeInvalid();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeInvalid() {
		try {
			fileWriter.write("Invalid Input\r\n");
			System.out.println("Invalid Input\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeResult(Student student) {
		try {
			fileWriter.write(student.getRollNo() + " " + student.getName() + " " + student.getFinalResult() + "\r\n");
			System.out.println(student.getRollNo() + " " + student.getName() + " " + student.getFinalResult() + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
