package com.example.bookmarkingapplication;

import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import android.content.Context;
import android.util.Log;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    // Constructor
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    // Getter and Setter methods
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    // Method to read users from the CSV file
    public static ArrayList<User> readUsersFromCSV(Context context) {
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader;
        try {
            InputStream is = context.getAssets().open("loginDetails.csv");
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|\\|");
                int id = Integer.parseInt(parts[0].split("=")[1]);
                String username = parts[1].split("=")[1];
                String password = parts[2].split("=")[1];
                users.add(new User(id, username, password));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LoginError", "Error reading CSV file",e);
        }
        return users;
    }

    public ArrayList<String> getUserUrls(Context context, int userId) {
        ArrayList<String> urls = new ArrayList<>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        boolean isUserSection = false;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.equals("[" + userId + "]")) {
                    isUserSection = true;
                    continue;
                }

                if (isUserSection) {
                    if (line.isEmpty()) {
                        break; // End of this user's section
                    }
                    urls.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }

}
