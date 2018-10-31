package ro.happydevs.intellifin.ai.generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.ai.models.IntelliAINeedsModel;

@Component
public class IntelliAI {

    @Autowired
    public IntelliAINeedsModel intelliAINeedsModel;


    public void doStuff() {

        intelliAINeedsModel.afiseaza();


    }

    public void doStuff2() {
        //1. Refoloseste ala de deasupra
        intelliAINeedsModel.afiseaza();

        //2. Reinstantiaza clasa
    }
}
