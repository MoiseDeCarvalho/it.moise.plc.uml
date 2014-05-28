package it.moise.plc.uml;

import java.io.*;
/**
 * 
 * Classse che mi permette di costruire un oggetoo che scrive su file di testo
 * @author De Carvalho Moise
 */
public class ScriviSuFile {
	private String nomeFile = null;
	private FileOutputStream printFile = null;

	public ScriviSuFile(String nomeFile) {
		this.nomeFile = nomeFile;
		try {
			// verifico se il file eseiste, se Si lo cancello
			if (new File(nomeFile).exists())
					this.DeleteFile(nomeFile);
			
			printFile = new FileOutputStream(nomeFile);
		} catch (FileNotFoundException exception) {
			System.exit(1);
		}
		
	}

	public void scrivi(String testo) {
		PrintStream scrivi = new PrintStream(printFile);
			scrivi.println(testo);
	}

	public void DeleteFile(String nomeFile) {
		// Creo un oggetto file
		File f = new File(nomeFile);

		// Mi assicuro che il file esista
		if (!f.exists())
			throw new IllegalArgumentException(
					"\nIl File o la Directory non esiste: " + nomeFile);

		// Mi assicuro che il file sia scrivibile
		if (!f.canWrite())
			throw new IllegalArgumentException(
					"\nNon ho il permesso di scrittura: " + nomeFile);

		// Se � una cartella verifico che sia vuota
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0)
				throw new IllegalArgumentException("La Directory non � vuota: "
						+ nomeFile);

			// Profo a cancellare
			boolean success = f.delete();

			// Se si � verificato un errore...
			if (!success)
				throw new IllegalArgumentException("Cancellazione fallita");
		}
	}
}
