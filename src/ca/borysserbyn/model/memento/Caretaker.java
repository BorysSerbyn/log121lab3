package ca.borysserbyn.model.memento;


public class Caretaker {


    ArrayList<Memento> savedImage = new ArrayList<Memento>();

    public void addMemento(Memento m){ savedImage.add(m); }

    public Memento getMemento(int index){ return savedImage.get(index); }

}
