/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Forum;
import com.mycompany.service.ServiceForum;

/**
 *
 * @author pc
 */
public class ModifForumForm extends Form{
     Forum current;
     public ModifForumForm(Form previous, Forum f) {

        setTitle("Update Forum");
        setLayout(BoxLayout.y());
        System.out.println("Forum a modif "+f);
        int id = f.getId();
        TextField tfTitleM = new TextField();
  
        TextField tfDescriptionM = new TextField();
        TextField tfIdM = new TextField();
        
        Button btnValider = new Button("Update Forum");
        
        tfTitleM.setText(f.getName());
        tfDescriptionM.setText(f.getDescription());
        
        addAll(tfTitleM, tfDescriptionM, btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitleM.getText().length() == 0) || (tfDescriptionM.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                        Forum f = new Forum(tfTitleM.getText(), tfDescriptionM.getText(),id);
                        if (ServiceForum.getInstance().modifForum(f)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            Preferences.clearAll();
                            new ListeForumForm().show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }
}
