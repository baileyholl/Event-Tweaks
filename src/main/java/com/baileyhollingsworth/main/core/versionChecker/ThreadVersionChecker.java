package com.baileyhollingsworth.main.core.versionChecker;

import com.baileyhollingsworth.main.core.EventTweaks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * Created by Bailey Hollingsworth on 12/19/16.
 */
public class ThreadVersionChecker extends Thread{

    public ThreadVersionChecker() {
        setName("Event Tweaks Checking Thread");
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        try {
            URL url = new URL(EventTweaks.URL);
            BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
            VersionChecker.version = r.readLine();
            r.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
