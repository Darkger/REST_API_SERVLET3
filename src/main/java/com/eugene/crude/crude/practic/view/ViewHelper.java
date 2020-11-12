package com.eugene.crude.crude.practic.view;

import java.io.IOException;

public class ViewHelper {
    String check="full";

   public void  mainHelper() throws IOException {
       ViewSetter viewSetter= new ViewSetter();
      View userView = new UserView();
      View postView = new PostView();
      View regionView = new RegionView();
      viewSetter.setView(userView);

       while (!check.equals("N")) {
          check= viewSetter.Do();
         switch (check){
             case "POST": {
                 viewSetter.setView(postView);
                 break;
             }
             case"REG":{
                 viewSetter.setView(regionView);
                 break;
             }
             case"USER":{
                 viewSetter.setView(userView);
                 break;
             }
           }
       }
   }

}
