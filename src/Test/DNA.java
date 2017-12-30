package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


public class DNA {

	private static int dnaLength = 100;
	private int[] dna = new int[dnaLength];

	// DNA besteht bei diesem Robot aus 4 Möglichkeiten (1, 2)
	// 0 bedeutet: Robot macht nix
	// 1 bedeutet: Robot feuert nen Bullet
	
	//ja eig könnte man das iwie schön mit State/Strategy machen, aber das ist mir im Moment zu viel Aufwand

	public static void main(String[] args) {
		DNA.createDNAOnce();
	}
	
	public DNA() {
		this.dna = readDNA();
	}

	public int[] getDNA() {
		return dna;
	}

	public int[] readDNA() {
		File dnatxt = new File(
				"C:\\Users\\Nikolai\\Documents\\DHBW\\5.Semester\\Robotik\\Git Repo\\Robots\\src\\TestRobot1\\DNA.txt");

		String dna = null;
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(dnatxt));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			dna = stringBuffer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return StringToDnaArray(dna);

	}

	public static void createDNAOnce() {
		int[] initalDNA = new int[dnaLength];
		for (int i = 0; i < initalDNA.length; i++) {
			initalDNA[i] = ((int) (Math.random() * 2));
		}

		try {
			File dnatxt = new File(
					"C:\\Users\\Nikolai\\Documents\\DHBW\\5.Semester\\Robotik\\Git Repo\\Robots\\src\\TestRobot1\\DNA.txt");
			Writer writer = new FileWriter(dnatxt);
			writer.write(DnaArrayToString(initalDNA));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String DnaArrayToString(int[] dna) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < dna.length; i++) {
			s.append(dna[i] + " ");
		}
		return s.toString();
	}

	public static int[] StringToDnaArray(String s) {
		String[] teile = s.split(" ");
		int[] array = new int[teile.length];

		for (int i = 0; i < teile.length; i++) {
			array[i] = Integer.parseInt(teile[i]);
		}
		return array;
	}
}
