package database;

import android.app.Application;

/**
 * Created by Matheus on 06/11/2016.
 */

public class Marco extends Application {

    private MyStateManager myStateManager = new MyStateManager();

    public MyStateManager getStateManager(){
        return myStateManager ;
    }
}

class MyStateManager {
   private String uid; //uid do usuário
    MyStateManager() {

    }

    String getState() {
        return uid;
    }
    void setUid(String setUid) {
        uid = setUid;
    }
}


