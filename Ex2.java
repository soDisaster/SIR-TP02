import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;



public class Ex2 {

	/* Nom du fichier utilisé, changer chemin si besoin */
	
	private String fichier = "log-reco.txt";

	/**
	 * Méthode permettant de creer un fichier avec le nom de chaque utilisateur.
	 * @param utilisateurs
	 */
	public void creerFichierUtilisateurs(Map<Integer, String> utilisateurs){

		String prenom;

		/* Lecture */

		try{
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			Integer i = 0;
			while ((ligne=br.readLine())!=null){
				String [] tab = ligne.split(";");
				prenom = tab[1];
				if(!(utilisateurs.containsValue(prenom))){
					utilisateurs.put(i, prenom);
				}
				i++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		/* Ecriture */
		
		try {
			FileWriter fw = new FileWriter("personnes.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			for(String s : utilisateurs.values()){
				fichierSortie.println(s); 
			}
			fichierSortie.close();
			System.out.println("Le fichier personnes.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	/**
	 * Méthode permettant de creer un fichier avec le nom de chaque theme.
	 * @param themes
	 */
	public void creerFichierThemes(Map<Integer, String> themes){

		String theme;

		/* Lecture */

		try{
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			Integer i = 0;
			while ((ligne=br.readLine())!=null){
				String [] tab = ligne.split(";");
				theme = tab[2];
				if(!(themes.containsValue(theme))){
					themes.put(i, theme);
				}
				i++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		/* Ecriture */

		try {
			FileWriter fw = new FileWriter("themes.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			for(String s : themes.values()){
				fichierSortie.println(s); 
			}
			fichierSortie.close();
			System.out.println("Le fichier themes.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	/**
	 * Méthode créant une matrice permettant de connaitre le nombre de consultations par themes
	 * pour chaque utilisateur et inversement.
	 * @param matrice
	 * @param themes
	 * @param utilisateurs
	 */
	public void creerFichiersMut2EtMut2Binaire(Map<String, Integer> matrice, Map<Integer, String> themes, Map<Integer, String> utilisateurs){

		String prenom;
		String theme;

		/* Lecture */

		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				String tab[] = ligne.split(";");
				prenom = tab[1];
				theme = tab[2];
				if(!(matrice.containsKey(prenom + "-" + theme))){
					matrice.put(prenom + "-" + theme, 1);
				}
				else{
					Integer nombreConsultations = matrice.get(prenom + "-" + theme);
					nombreConsultations++;
					matrice.put(prenom + "-" + theme, nombreConsultations);
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		/* Ecriture */

		try {
			FileWriter fw = new FileWriter("Mut2.txt");
			FileWriter fw2 = new FileWriter("Mut2Binaire.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			BufferedWriter bw2 = new BufferedWriter (fw2);
			/* Le fichier Mut2.txt */
			PrintWriter fichierSortie = new PrintWriter (bw);
			/* Le fichier Mut2Binaire.txt */
			PrintWriter fichierSortie2 = new PrintWriter (bw2);
			for(String u : utilisateurs.values()){
				for(String t : themes.values()){
					Integer j = matrice.get(u + "-" + t); 
					if(j == null){
						fichierSortie.print("0 ");
						fichierSortie2.print("0 ");
					}
					else{
						fichierSortie.print(j + " ");
						fichierSortie2.print("1 ");
					}
				}
				fichierSortie.println();
				fichierSortie2.println();
			}
			fichierSortie.close();
			fichierSortie2.close();
			System.out.println("Le fichier Mut2.txt a été créé!"); 
			System.out.println("Le fichier Mut2Binaire.txt a été créé!");
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * Méthode permettant de creer la matrice binaire.
	 */
	public int[][] creerMatriceBinaire(Map<Integer, String> themes, Map<Integer, String> utilisateurs){
		int[][] matrice2Binaire = new int [utilisateurs.size()][themes.size()];
		try{
			/* Lecture Mut2Binaire.txt */
			
			InputStream ips=new FileInputStream("Mut2Binaire.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			int l = 0;
			int c = 0;
			String ligne;
			while ((ligne=br.readLine())!=null){
				c = 0;
				String tab[] = ligne.split(" ");
				for(int i = 0; i < tab.length; i++){
					matrice2Binaire[l][c] = Integer.parseInt(tab[i]);
					c++;
				}
				l++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		return matrice2Binaire;
	}

	/**
	 * Méthode permettant de creer le fichier de la matrice MTT .
	 */
	public void creerFichierMatriceMTT(int [][] matrice2Binaire, Map<Integer, String> themes){
		
		float [][] matriceDistance = new float[themes.size()][themes.size()];
		
		/* Calcul de distance */
		
		for(int l = 0; l < matriceDistance.length; l++){
			for(int c = 0; c < matriceDistance[l].length; c++){
				float enCommun = 0;
				float denominateur = 0;
				for(int i = 0; i < matrice2Binaire.length; i++){
					if(matrice2Binaire[i][l] == 1 && matrice2Binaire[i][c] == 1){
						enCommun++;
					}
					if(matrice2Binaire[i][l] == 1 || matrice2Binaire[i][c] == 1){
						denominateur++;
					}
				}
				
				matriceDistance[l][c] = 1 - (enCommun/denominateur);
				
			}
		}

		/* Ecriture fichier Mtt.txt */
		try {
			FileWriter fw = new FileWriter("Mtt.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw);
			for(int i = 0; i < matriceDistance.length; i++){
				for(int j = 0; j < matriceDistance.length; j++){
					fichierSortie.print(matriceDistance[i][j] + " ");
				}
				fichierSortie.println();
			}
			fichierSortie.close();
			System.out.println("Le fichier Mtt.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}
	
	/**
	 * Méthode permettant de creer la matrice MTT.
	 */
	public float[][] creerMatriceMTT(Map<Integer, String> themes){
		float[][] matriceMTT = new float [themes.size()][themes.size()];
		try{
			InputStream ips=new FileInputStream("Mtt.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			int l = 0;
			int c = 0;
			String ligne;
			while ((ligne=br.readLine())!=null){
				c = 0;
				String tab[] = ligne.split(" ");
				for(int i = 0; i < tab.length; i++){
					matriceMTT[l][c] = Float.parseFloat(tab[i]);
					c++;
				}
				l++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		return matriceMTT;
	}
	
	/**
	 * Méthode permettant de creer la matrice MTT binaire ainsi que le fichier MTT binaire .
	 */
	public int[][] creerFichierMttBinaire(float [][] matriceMTT){
		int[][] matriceMTTBinaire = new int [matriceMTT.length][matriceMTT.length];
		try {
			FileWriter fw = new FileWriter("Mtt-binaire.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw);
			for(int i = 0; i < matriceMTT.length; i++){
				for(int j = 0; j < matriceMTT.length; j++){
					/* Si les distances sont inférieurs à 0,5, les thèmes sont liés */
					if(matriceMTT[i][j] < 0.5){
						fichierSortie.print("1 ");
						matriceMTTBinaire[i][j] = 1;
					}
					else{
						fichierSortie.print("0 ");
						matriceMTTBinaire[i][j] = 0;
					}
				}
				fichierSortie.println();
			}
			fichierSortie.close();
			System.out.println("Le fichier Mtt-binaire.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		return matriceMTTBinaire;
	}
	
	/**
	 * Méthode permettant de connaitre les recommandations que l'on doit faire aux utilisateurs.
	 */
	public void recommandations(int [][] matrice2Binaire, int[][] matriceMTTBinaire, Map <Integer, String> themes, Map<Integer, String> utilisateurs ){
		
		HashMap<Integer, Integer> themesAssocies = new HashMap<Integer, Integer>();
		int [][] usagersThemesRecommandes = new int[utilisateurs.size()][themes.size()];
		
		/* Stockés dans une Map les thèmes associés
		  En evitant  par exemple d'associer Bricolage et Bricolage */
		
		for(int i = 0; i < matriceMTTBinaire.length; i++){
			for(int j = 0; j < matriceMTTBinaire.length; j++){
				if(matriceMTTBinaire[i][j] == 1 && i != j){
					if(!(themesAssocies.containsKey(i))){
						themesAssocies.put(i,j);
					}
				}
			}
		}
		
		/* Comparer la Map des thèmes associés avec la Matrice Binaire Usagers Thèmes*/
		
		for(int i = 0; i < matrice2Binaire.length; i++){
			for(int j = 0; j < matrice2Binaire[i].length; j++){
				if(themesAssocies.containsKey(j)){
					int valeurAssociee = themesAssocies.get(j);
					if(matrice2Binaire[i][valeurAssociee] == 0 && matrice2Binaire[i][j] == 1 ){
						usagersThemesRecommandes[i][j] = 1;
					}
				}
			}
		}
		/* Ecriture */
		
		try {
			FileWriter fw = new FileWriter("MutR.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw);
			for(int i = 0; i < usagersThemesRecommandes.length; i++){
				for(int j = 0; j < usagersThemesRecommandes[j].length; j++){
						fichierSortie.print(usagersThemesRecommandes[i][j] + " ");
				}
				fichierSortie.println();
			}
			fichierSortie.close();
			System.out.println("Le fichier MutR.txt a été créé!"); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * Main 
	 * Execution des trois méthodes
	 * @param args
	 */
	public static void main(String[] args){
		Map<String, Integer> matrice2 = new HashMap<String, Integer>();
		Map<Integer, String> utilisateurs = new HashMap<Integer, String>();
		Map<Integer, String> themes = new HashMap<Integer, String>();
		int [][] matrice2Binaire = new int [themes.size()][utilisateurs.size()];
		float [][] matriceMTT = new float [themes.size()][utilisateurs.size()];
		int [][] matriceMTTBinaire = new int [matriceMTT.length][matriceMTT.length];
		Ex2 ex2 = new Ex2();
		ex2.creerFichierUtilisateurs(utilisateurs);
		ex2.creerFichierThemes(themes);
		ex2.creerFichiersMut2EtMut2Binaire(matrice2, themes, utilisateurs);
		matrice2Binaire = ex2.creerMatriceBinaire(themes, utilisateurs);
		ex2.creerFichierMatriceMTT(matrice2Binaire, themes);
		matriceMTT = ex2.creerMatriceMTT(themes);
		matriceMTTBinaire = ex2.creerFichierMttBinaire(matriceMTT);
		ex2.recommandations(matrice2Binaire, matriceMTTBinaire, themes, utilisateurs);
	}

}
