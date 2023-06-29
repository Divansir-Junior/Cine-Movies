package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ActionMovie extends Movie implements Serializable {
    private static final long serialVersionUID = 1L; 

    private String actionType;

    public ActionMovie(String name, String nameDirector, String filmGenre, String actionType) {
        super(name, nameDirector, filmGenre);
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(actionType);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        actionType = (String) in.readObject();
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo de ação: " + actionType;
    }
}
