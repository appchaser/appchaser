/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Forum;
import com.mycompany.entites.comments;
import com.mycompany.service.ServiceComments;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ListPostForm extends Form{
     public ArrayList<Forum> forums;
    public ArrayList<comments> posts;
    Form current;
    // static  TextField tfIdF = new TextField();
//liste des post (forum id ) 

    public ListPostForm(Form previous, Forum f) {
        
        setTitle("List Poste");

        setLayout(BoxLayout.y());

        TextField Title = new TextField("", "Post Title");
        TextField Description = new TextField("", "description Post");
        Button btnValider = new Button("Add Post");
        Button Modifbtn = new Button("Modif valider ");
               
        addAll(Title, Description, btnValider,Modifbtn);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Title.getText().length() == 0) || (Description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        comments p = new comments( Title.getText(),Description.getText(), f.getId());
                        if (ServiceComments.getInstance().addPost(p, f.getId())) {
                            Dialog.show("connectedd", "succed", new Command("OK"));
                            new ListPostForm(previous, f).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
         System.out.println("Id Forum=> " + f.getId());
        posts = ServiceComments.getInstance().getPosts(f.getId());
        System.out.println(f.getComments());
         for (comments obj : posts) {

            System.out.println("postttt=> " + f.getComments());
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("Title : " + obj.getAutor());
             spTitle.addActionListener(e -> {
                ServiceComments.getInstance().detailPost(obj.getId());
                System.out.println("heeeere"+obj.getId());
              //  new ListPostForm(previous,obj).show();
                
                        
            });
                    sp.setText("Description : " + obj.getContent());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServiceComments.getInstance().deletePost(obj.getId());
                new ListPostForm(previous, f).show();
            });
            Modif.addActionListener((ActionEvent evt) -> {
               new ModifierPostForm(previous,obj).show();

            });
            addAll(spTitle, Delete, Modif, sp);
        }
        // sp.setText(new ServiceForum().getAllForums().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    

}
    
    
}
