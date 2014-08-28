package com.mrprez.gencross.ui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.disk.RepositoryManager;
import com.mrprez.gencross.ui.errorframe.ErrorFrame;
import com.mrprez.gencross.ui.persoframe.PersoFrame;
import com.mrprez.gencross.ui.persoframe.PersoFrameTransferHandler;

public class GenCrossUI {
	public final static String PERSONNAGE_REPOSITORY_NAME = "repository";
	public final static String TEMP_REPOSITORY_NAME = "temp";
	private static GenCrossUI instance;
	private File tempDirectory;
	private PersoFrame persoFrame;
	private Personnage personnage = new Personnage();
	private List<Personnage> lastModifications = new ArrayList<Personnage>(10);
	private boolean saved = true;
	private File lastSave = null;
	private RepositoryManager repositoryManager;
	private PersonnageFactory personnageFactory;
	private boolean mj = false;
	

	public static void main(String[] args) throws InterruptedException {
		try {
			File executionDirectory  = getExecutionDirectory();
			File personnageRepository = new File(executionDirectory, PERSONNAGE_REPOSITORY_NAME);
			instance = new GenCrossUI(personnageRepository);
			if(args.length>0){
				try{
					File file = new File(args[0]);
					instance.personnage = instance.personnageFactory.loadPersonnageFromGcr(file);
					instance.setLastSave(file);
					instance.persoFrame.reinit();
				}catch(ClassNotFoundException cnfe){
					instance.persoFrame.setVisible(true);
					JOptionPane.showMessageDialog(instance.persoFrame, "Plugin manquant: "+cnfe.getMessage(), "Plugin manquant", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (Exception e) {
			ErrorFrame errorFrame = new ErrorFrame(e);
			errorFrame.setVisible(true);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public GenCrossUI(File personnageRepository) throws HeadlessException, CloneNotSupportedException, IOException, URISyntaxException{
		repositoryManager = new RepositoryManager(personnageRepository);
		personnageFactory = new PersonnageFactory(repositoryManager.getRepositoryClassLoader());
		tempDirectory = new File(getExecutionDirectory().getParentFile(), TEMP_REPOSITORY_NAME);
		
		persoFrame = new PersoFrame();
		persoFrame.setIconImage(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/icone_GenCross.jpg")));
		persoFrame.setModifyIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/modify-icone.png"))));
		persoFrame.setTransferHandler(new PersoFrameTransferHandler());
		persoFrame.setVisible(true);
	}
	
	public static File getExecutionDirectory() throws URISyntaxException{
		URL jarUrl = GenCrossUI.class.getProtectionDomain().getCodeSource().getLocation();
		File jarFile = new File(jarUrl.toURI());
		return jarFile.getParentFile();
	}
	
	public static void reinit() throws HeadlessException, CloneNotSupportedException, IOException, URISyntaxException{
		GenCrossUI.getCurrentPersoFrame().getHistoryFrame().dispose();
		GenCrossUI.getCurrentPersoFrame().getEditCommentFrame().dispose();
		GenCrossUI.getCurrentPersoFrame().getViewCommentFrame().dispose();
		GenCrossUI.getCurrentPersoFrame().dispose();
		initInstance(instance.repositoryManager.getRepository());
	}
	
	public static GenCrossUI initInstance(File repository) throws HeadlessException, CloneNotSupportedException, IOException, URISyntaxException{
		instance = new GenCrossUI(repository);
		return instance;
	}

	
	public File getTempDirectory() {
		return tempDirectory;
	}
	
	public static GenCrossUI getInstance() {
		return instance;
	}
	
	public static Personnage getCurrentPersonnage(){
		return instance.personnage;
	}
	
	public Personnage getPersonnage(){
		return personnage;
	}
	
	public void setPersonnage(Personnage personnage){
		this.personnage = personnage;
	}
	
	public RepositoryManager getRepositoryManager() {
		return repositoryManager;
	}
	
	public PersonnageFactory getPersonnageFactory(){
		return personnageFactory;
	}
	
	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
	public void saveModification() throws CloneNotSupportedException{
		saved = false;
		if(lastModifications.size()==10){
			lastModifications.remove(0);
		}
		lastModifications.add(personnage.clone());
		System.out.println("saveModification: "+personnage+" saved in "+lastModifications.get(lastModifications.size()-1));
	}
	
	public boolean isCancelAvailable(){
		return lastModifications.size()>1;
	}
	
	public void clearLastModifications(){
		System.out.println("clearLastModifications");
		lastModifications.clear();
	}
	
	public boolean cancel() throws CloneNotSupportedException{
		if(lastModifications.size()<=1){
			return false;
		}
		lastModifications.remove(lastModifications.size()-1);
		System.out.print("cancel: "+lastModifications.get(lastModifications.size()-1)+" loaded as ");
		personnage = lastModifications.get(lastModifications.size()-1).clone();
		System.out.println(personnage);
		
		persoFrame.reinit();
		return true;
	}
	
	public boolean isMj() {
		return mj;
	}
	public void setMj(boolean mj) {
		this.mj = mj;
		persoFrame.reinitMenuBar();
		persoFrame.reinitPointsPanel();
	}
	
	public File getLastSave() {
		return lastSave;
	}
	public void setLastSave(File lastSave) {
		this.lastSave = lastSave;
		
		persoFrame.reinitMenuBar();
	}
	
	public PersoFrame getPersoFrame(){
		return persoFrame;
	}
	
	public static PersoFrame getCurrentPersoFrame(){
		return instance.persoFrame;
	}

}
