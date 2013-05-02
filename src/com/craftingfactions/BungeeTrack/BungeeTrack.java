package com.craftingfactions.BungeeTrack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import com.google.common.eventbus.Subscribe;

public class BungeeTrack extends Plugin implements Listener {

    private Logger logger = null;

    public Logger getLogger() {
	return this.logger;
    }

    public void onEnable() {

	this.logger = new PluginLogger(this);

	ProxyServer.getInstance().getPluginManager().registerListener(this);

	getLogger().log(Level.INFO, "Enabled BungeeTrack");

    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
	getLogger().log(Level.INFO, "got login event");
	try {

	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	    Date today = Calendar.getInstance().getTime();

	    String reportDate = df.format(today);

	    PrintWriter out = new PrintWriter(new BufferedWriter(
		    new FileWriter("BungeeTrack.txt", true)));
	    out.println(event.getConnection().getAddress().getAddress()
		    .toString()
		    + " | "
		    + event.getConnection().getName()
		    + " | "
		    + event.getConnection().getVirtualHost().getHostString()
		    + " | " + reportDate);
	    out.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
