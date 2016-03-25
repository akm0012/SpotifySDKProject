package com.mobiquity.amarshall.spotifysync;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import junit.framework.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import kaaes.spotify.webapi.android.models.Track;

public class Client extends AsyncTask<Track, Void, Void> {
 
   String dstAddress;
   int dstPort;
   String response = "";

   public Client(String addr, int port) {
      dstAddress = addr;
      dstPort = port;
   }
 
   @Override
   protected Void doInBackground(Track... arg0) {

      Log.i("MinaServer", "starting connection");

      Socket socket = null;
 
      try {
         Log.i("MinaServer", "try block");
         socket = new Socket(dstAddress, dstPort);

         Log.i("MinaServer", "socket initalized");
         PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
         Gson gson = new Gson();

         for(Track track : arg0) {
            Log.i("MinaServer", track.name);
            String json = gson.toJson(track);
            json += "\n";
            pw.write(json);
         }

         pw.flush();
 
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
               1024);
         byte[] buffer = new byte[1024];
 
         int bytesRead;
         InputStream inputStream = socket.getInputStream();
 
         /*
          * notice: inputStream.read() will block if no data return
          */
         while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
            response += byteArrayOutputStream.toString("UTF-8").trim();
         }

         pw.close();
         byteArrayOutputStream.flush();
         byteArrayOutputStream.close();

      } catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         response = "UnknownHostException: " + e.toString();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         response = "IOException: " + e.toString();
      } finally {
         if (socket != null) {
            try {
               socket.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      return null;
   }
 
   @Override
   protected void onPostExecute(Void result) {
      Gson gson = new Gson();
      TestSongListModel model = gson.fromJson(response.trim(), TestSongListModel.class);
      Log.i("MinaServer", response);
      super.onPostExecute(result);
   }
 
}