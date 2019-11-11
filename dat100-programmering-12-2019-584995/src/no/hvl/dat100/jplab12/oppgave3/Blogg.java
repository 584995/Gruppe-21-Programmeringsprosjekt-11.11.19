package no.hvl.dat100.jplab12.oppgave3;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;
import no.hvl.dat100.jplab12.oppgave2.Bilde;
import no.hvl.dat100.jplab12.oppgave2.Tekst;

public class Blogg {

	Innlegg[] innleggtabell;
	int nesteledig;

	public Blogg() {
		innleggtabell = new Innlegg[20];
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				return i;
			}
		}
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				return true;
			}
		}
		return false;
	}

	public boolean ledigPlass() {
		if (nesteledig == innleggtabell.length) {
			return false;
		}
		return true;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		if (ledigPlass() && !finnes(innlegg)) {
			innleggtabell[nesteledig] = innlegg;
			nesteledig++;
			return true;
		}
		return false;
	}
															
	public String toString() {
		String temp = nesteledig + "\n";
		for (int i = 0; i < nesteledig; i++) {
			if(innleggtabell[i] instanceof Bilde) {
				temp += ((Bilde)innleggtabell[i]).toString();				
			} else {
				temp += ((Tekst)innleggtabell[i]).toString();
			}
		}
		return temp;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		Innlegg[] ny = new Innlegg[innleggtabell.length * 2];
		for (int i = 0; i < nesteledig; i++) {
			ny[i] = innleggtabell[i];
		}
		innleggtabell = ny;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {

		if (finnes(innlegg)) {
			return false;
		} else {
			if(!ledigPlass()) {
				utvid();
			}
			leggTil(innlegg);
			return true;
		}
		
		
	}
	
	public boolean slett(Innlegg innlegg) {
		for (int i = 0; i < innleggtabell.length; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				innleggtabell[i] = innleggtabell[nesteledig - 1];
				innleggtabell[nesteledig - 1] = null;
				nesteledig--;
				return true;
			}
		}
		return false;
	}
	
	public int[] search(String keyword) {
		int teller = 0;
		int[] orden = new int[nesteledig];
		for (int i = 0; i < nesteledig; i++) {
			if (((Tekst)innleggtabell[i]).toString().contains(keyword)) {
				orden[teller] = i;
				teller++;
			}
		}
		int[] ny = new int[teller];
		for (int i = 0; i < ny.length; i++) {
			ny[i] = orden[i];
		}
		return ny;

	}
}