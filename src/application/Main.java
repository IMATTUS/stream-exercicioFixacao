package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import services.EmployeeServices;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

//		System.out.print("Enter full file path: ");
//		String path = sc.nextLine();
		String path = "in.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Employee> list = new ArrayList<>();
			
			System.out.println("Enter Salary: ");
			double compSalary = sc.nextDouble();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.println("Email from employee whose salary is more than " + String.format("%.2f", compSalary));
//			Comparable<Double> comp = c1 -> c1.compareTo();
			List<String> emails = list.stream()
					.filter(x->x.getSalary()>=compSalary)
					.map(x->x.getEmail())
					.collect(Collectors.toList());
			
			emails.forEach(System.out::println);
			
			EmployeeServices es = new EmployeeServices();
			double sum  = es.filteredSum(list,  p->p.getName().charAt(0)=='M');
			
			System.out.println("Sum of salary of peoples whose name starts with 'M': " + sum);
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();

	}

}
