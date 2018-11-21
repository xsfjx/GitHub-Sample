package com.githubsample.tools.enums;

public enum TypeEnum {

    status(0) , pinCode(1) , gallery(2);
    private int id;

    TypeEnum(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public boolean isStatus (){
        return getId() == status.id;
    }
    public boolean isPinCode (){
        return getId() == pinCode.id;
    }
    public boolean isGallery (){
        return getId() == gallery.id;
    }
}
