package com.alexandria_library.data.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.alexandria_library.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class HSQLDBHelper {
    /******
     * Copy database to device at login page, cause login page is first page landed
     */
    public static void copyDatabaseToDevice(Context context){
        final String DB_Path = "db";
        String [] assetNames;
        File dataDirectory = context.getDir(DB_Path, Context.MODE_PRIVATE);
        AssetManager assetManager = context.getAssets();

        try{
            assetNames = assetManager.list(DB_Path);
            for(int i = 0; i<assetNames.length; i++){
                assetNames[i] = DB_Path + "/" + assetNames[i];
            }
            copyAssetsToDirectory(context, assetNames, dataDirectory);
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch(final IOException e){
            System.out.println("error in copy database to device");
            e.printStackTrace();
        }
    }

    private static void copyAssetsToDirectory(Context context, String [] assets, File directory) throws IOException{
        AssetManager assetManager = context.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
