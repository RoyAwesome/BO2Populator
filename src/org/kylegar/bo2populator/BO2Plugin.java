package org.kylegar.bo2populator;

import java.io.FileNotFoundException;
import java.util.logging.Level;

import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.java.JavaPlugin;

public class BO2Plugin extends JavaPlugin {

	public static BO2Plugin thisPlugin;
	BlockPopulator bo2;
	
	@Override
	public void onDisable() {
		ConOut("BO2 Disabled");
		this.getServer().getWorlds().get(0).getPopulators().remove(bo2);
	}

	@Override
	public void onEnable() {
		thisPlugin = this;
		ConOut("BO2 Enabled! Adding BO2 Populator to default world!");
		bo2 = new BO2Populator();
		this.getServer().getWorlds().get(0).getPopulators().add(bo2);
		try {
			BO2ObjectManager.ReadBO2Files(thisPlugin.getDataFolder()+"/Data/");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	 public void prettyLog(final Level severity, final boolean version, final String message) {
	        final String prettyName = ("[" + this.getDescription().getName() + "]");
	        final String prettyVersion = ("[v" + this.getDescription().getVersion() + "]");
	        String prettyLogLine = prettyName;
	        if (version) {
	            prettyLogLine += prettyVersion;
	            this.getServer().getLogger().log(severity, prettyLogLine + message);
	        }
	        else {
	        	this.getServer().getLogger().log(severity, prettyLogLine + message);
	        }
	    }
	 final static boolean verbose = false;
	 public static void ConOut(String message){
		 
		 if(verbose) thisPlugin.prettyLog(Level.INFO, false, message);
	 }
	
	
	
	
}
