package me.zaksen.manafactory.mana;

public interface ManaContainer {

    default boolean send(int amount, ManaContainer receiver) {
        if(getCapacity() >= amount) {
            if(receiver.getFreeCapacity() >= amount) {
                receiver.recieve(amount, this);
                setCapacity(getCapacity() - amount);
            } else {
                int toSend = amount - receiver.getFreeCapacity();
                if(toSend == 0) {
                    return false;
                }
                receiver.recieve(toSend, this);
                setCapacity(getCapacity() - toSend);
            }
        } else {
            int toSend = getCapacity();
            if(toSend == 0) {
                return false;
            }
            receiver.recieve(toSend, this);
            setCapacity(getCapacity() - toSend);
        }
        return true;
    }

    default boolean recieve(int amount, ManaContainer sender) {
        if(amount > getFreeCapacity()) {
            setCapacity(getCapacity() + getFreeCapacity());
        } else {
            setCapacity(getCapacity() + amount);
        }
        return true;
    }

    int getCapacity();
    int getMaxCapacity();
    int getFreeCapacity();

    void setCapacity(int amount);
}
