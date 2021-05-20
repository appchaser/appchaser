/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Forum;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public class ServiceForum {
     public ArrayList<Forum> forums;
      public static ServiceForum instance = null;
    public boolean resultOK;
      private ConnectionRequest req;
      private ServiceForum() {
        req = new ConnectionRequest();
    }
      public static ServiceForum getInstance() {
        if (instance == null) {
            instance = new ServiceForum();
        }
        return instance;
    }
        public boolean addForum(Forum f) {
        String url = Statics.BASE_url+"/addForumJSON/new?namee="+f.getName()+"&description="+ f.getDescription()+"&email="+ f.getEmail(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public ArrayList<Forum> parseTasks(String jsonText) {
        try {
            forums = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Forum f = new Forum();
               

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setName(obj.get("name").toString());
                f.setEmail(obj.get("email").toString());
                f.setDescription(obj.get("description").toString());
                
                //System.out.println("hellooo"+obj.get("posts").getClass());
                
               // f.setPosts((ArrayList<Post>[]) obj.get("posts"));
                forums.add(f);
            }
        } catch (IOException ex) {

        }
        return forums;
    }
          public ArrayList<Forum> getAllForums() {
        String url = Statics.BASE_url + "/AllForums";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forums = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return forums;
    }
            public void deleteForum(int id) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_url + "/deleteForumJSON/" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
            public boolean modifForum(Forum f) {
        String url = Statics.BASE_url + "/updateForumJSON/"+f.getId()+"&description=" + f.getDescription(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
             public void detailForum(int id ) {
        String url = Statics.BASE_url + "/showForumJSON/"+id; //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
}
