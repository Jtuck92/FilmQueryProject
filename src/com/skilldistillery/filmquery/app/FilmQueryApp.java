package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		runMenu(input);

		input.close();

	}

	private void runMenu(Scanner scanner) {
		boolean continueApp = true;

		do {
			System.out.println("-Welcome to The Grande Film Archive-");
			System.out.println("+----------------------------------+");
			System.out.println("|       1. Search film by id       |");
			System.out.println("|                                  |");
			System.out.println("|     2. Search film by keyword    |");
			System.out.println("|                                  |");
			System.out.println("|             3. Close             |");
			System.out.println("+----------------------------------+");

			String choice = scanner.next();

			switch (choice) {

			case "1":
				lookupId(scanner);
				break;

			case "2":
				lookupKeyword(scanner);
				break;

			case "3":
				System.out.println("Goodbye...");
				continueApp = false;
				break;

			default:
				System.out.println("Query failed");
				break;
			}
		} while (continueApp);

	}

	private void lookupId(Scanner input) {
		Film lookupResult = null;
		System.out.println("Enter film identification number");

		try {
			lookupResult = db.findFilmById(input.nextInt());
		} catch (InputMismatchException e) {
			System.out.println("Query failed.");
		} finally {
			input.nextLine();
		}

		if (lookupResult == null)
			System.out.println("No film found");
		else
			System.out.println(lookupResult);
	}

	private void lookupKeyword(Scanner input) {
		System.out.println("Enter keyword: ");

		List<Film> filmsQueryResult = db.findFilmByKeyword(input.next());
		if (!filmsQueryResult.isEmpty()) {

			for (Film film : filmsQueryResult) {
				System.out.println(film);

			}
		} else
			System.out.println("No film found");
	}

}
