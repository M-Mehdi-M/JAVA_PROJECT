package org.poo.cb;

import java.util.*;

class UserManager { // SINGLETON DESIGN PATTERN
    private static UserManager instance;
    private List<Utilizator> utilizatori;

    private UserManager() {
        utilizatori = new ArrayList<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Running Main");
    }

    public void adaugaUtilizator(Utilizator utilizator) {
        utilizatori.add(utilizator);
    }

    public boolean userExistsWithEmail(String email) {
        for (Utilizator user : utilizatori) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Utilizator gasesteUtilizator(String email) {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getEmail().equals(email)) {
                return utilizator;
            }
        }
        return null;
    }
}
