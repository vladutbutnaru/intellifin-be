package ro.happydevs.intellifin.models.dto;

public class SingletonClass {

    Singleton s = null;

    public Singleton getSingleton(){
        if(s==null){
            s = new Singleton();
        }
        return s;

    }

}
