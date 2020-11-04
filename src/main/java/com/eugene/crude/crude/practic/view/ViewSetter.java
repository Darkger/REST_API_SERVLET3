package com.eugene.crude.crude.practic.view;

import java.io.IOException;

public class ViewSetter implements  DoAction {
    View view;
    public void setView(View view1){
        view=view1;
    }


    @Override
    public String Do() throws IOException {
        return view.routing();
    }
}
