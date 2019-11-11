package no.hvl.dat100.jplab12.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;
import no.hvl.dat100.jplab12.oppgave2.*;
import no.hvl.dat100.jplab12.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String MAPPE = System.getProperty("user.dir") + "/src/no/hvl/dat100/jplab12/tests/";

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String filnavn) {
		try {	
			Scanner reader = new Scanner(new File(MAPPE + filnavn));
			Blogg ny = new Blogg(Integer.parseInt(reader.nextLine()));
			while (reader.hasNextLine()) {
				if (reader.nextLine().equals(TEKST)) {
					ny.leggTil(new Tekst(Integer.parseInt(reader.nextLine()), reader.nextLine(), reader.nextLine(), 
										 Integer.parseInt(reader.nextLine()), 					 reader.nextLine()));
				} else {
					ny.leggTil(new Bilde(Integer.parseInt(reader.nextLine()), reader.nextLine(), reader.nextLine(), 
										 Integer.parseInt(reader.nextLine()), reader.nextLine(), reader.nextLine()));
				}
			}
			reader.close();
			return ny;
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Filen " + filnavn + " finnes ikke. \n" + e.getMessage());
			return null;
		}
	}
}
