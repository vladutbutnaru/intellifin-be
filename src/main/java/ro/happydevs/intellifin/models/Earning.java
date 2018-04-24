package ro.happydevs.intellifin.models;

import ro.happydevs.intellifin.models.GenericModel;

public class Earning extends Transaction {

    private int sourceId;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}


