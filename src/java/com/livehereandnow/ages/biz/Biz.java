/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livehereandnow.ages.biz;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Singleton;

/**
 *
 * @author mark
 */
@ManagedBean
//@Singleton
@SessionScoped
//@RequestScoped
public class Biz {

    private String NOCARD = "http://2nd2go.org/ages/img/p1000.jpg";
    private String[] addr = new String[30];

    private String IMG_DIR = "http://2nd2go.org/ages/img/";
    private String username;
    private String player;

    private boolean notAllowNewGame;

    public boolean isNotAllowNewGame() {
        return notAllowNewGame;
    }

    public boolean isAllowed() {
        if (player.equalsIgnoreCase("max")) {
            return true;
        }
        if (player.equalsIgnoreCase("amy")) {
            return true;
        }
        return false;
    }

    public void setNotAllowNewGame(boolean notAllowNewGame) {
        this.notAllowNewGame = notAllowNewGame;
    }

    public String getCheckedUsername() {
        return player;
    }

    public void setCheckedUsername(String checkedUsername) {
        this.player = checkedUsername;
    }
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkPassword() {
        player = "guest";
        System.out.println("checkPassword   ???checking user/pass: " + username + "/" + password);
        if (username.equalsIgnoreCase("max") && password.equals("123")) {
            player = "max";
        }
        if (username.equalsIgnoreCase("amy") && password.equals("321")) {
            player = "amy";
        }
        if (username.equalsIgnoreCase("guest")) {
            notAllowNewGame = true;
        } else {
            notAllowNewGame = false;

        }

//        username = "";
    }

    public Biz() {
        init();
    }
    int removeCnt;

    private void init() {
        notAllowNewGame = true;
        username = "guest";
        password = "***";
        player = "guest";

        System.out.println("...init");
        removeCnt = 0;
//        int p = 1000;
        for (int k = 0; k <= 13; k++) {
            addr[k] = IMG_DIR + "p" + (1000 + k) + ".jpg";
        }

        for (int k = 14; k <= 29; k++) {
            addr[k] = IMG_DIR + "p" + (1033 + k) + ".jpg";
        }

    }

    public void doNewGame() {
        init();
    }

    public String getAddr(int k) {
        return addr[k];
    }

    public void setAddr(int k, String str) {
        removeCnt++;

        addr[k] = str;
    }

    public void doStep1() {
        System.out.println("...doStep1, remove first 3 cards");

        if (!addr[1].equals(NOCARD)) {
            removeCnt++;
        }
        if (!addr[2].equals(NOCARD)) {
            removeCnt++;
        }
        if (!addr[3].equals(NOCARD)) {
            removeCnt++;
        }

        addr[1] = "http://2nd2go.org/ages/img/p1000.jpg";
        addr[2] = "http://2nd2go.org/ages/img/p1000.jpg";
        addr[3] = "http://2nd2go.org/ages/img/p1000.jpg";

//        setAddr(1, "http://2nd2go.org/ages/img/p1000.jpg");
//        setAddr(2, "http://2nd2go.org/ages/img/p1000.jpg");
//        setAddr(3, "http://2nd2go.org/ages/img/p1000.jpg");
//      
    }

    public void doStep2() {
        System.out.println("...doStep2, align left");
// addr[4]= "http://2nd2go.org/ages/img/p1000.jpg";
        //   removeCnt = 0;
        for (int safe = 0; safe <= 12; safe++) {
            for (int k = 1; k <= 12; k++) {
                if (addr[k].equalsIgnoreCase(NOCARD)) {
                    for (int m = k + 1; m <= 13; m++) {
                        addr[m - 1] = addr[m];
                    }
                    addr[13] = NOCARD;
                }
            }
        }

    }

    public void doStep3() {
        System.out.println("...doStep3, refil");
        for (int k = 13 - removeCnt + 1; k <= 13; k++) {
            addr[k] = addr[k + removeCnt];
        }
    }

    public void doAct(int k) {
        if (!isAllowed()) {
            System.out.println("...NOT PLAYER, NOT ALLOW TO TOUCH CARD ROW ");
            return;
        }
        System.out.println(player+", take-card " + k);
        setAddr(k, "http://2nd2go.org/ages/img/p1000.jpg");
//        setAddr1("http://2nd2go.org/ages/img/p1000.jpg");
    }
}
