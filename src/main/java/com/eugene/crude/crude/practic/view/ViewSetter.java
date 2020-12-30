package com.eugene.crude.crude.practic.view;



public class ViewSetter implements  DoAction {
    View view;
    public void setView(View view1){
        view=view1;
    }


    @Override
    public String Do() {
        return view.routing();
    }
}
