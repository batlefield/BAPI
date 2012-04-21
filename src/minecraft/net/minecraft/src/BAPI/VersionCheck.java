package net.minecraft.src.BAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class VersionCheck
{
    public static String run(String url1)
    {
        try
        {
            URL url = new URL(url1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null)
            {
                return line;
            }

            reader.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }
}