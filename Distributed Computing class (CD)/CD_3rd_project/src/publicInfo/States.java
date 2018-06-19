/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicInfo;

/**
 *
 * @author diogo
 */
public enum States {

    REST(1) {

                @Override
                public String toString() {
                    return " REST";
                }

            }, DECD(2) {

                @Override
                public String toString() {
                    return " DECD";
                }

            }, MEET(3) {

                @Override
                public String toString() {
                    return " MEET";
                }

            }, DIST(4) {

                @Override
                public String toString() {
                    return " DIST";
                }
            }, WORK(5) {

                @Override
                public String toString() {
                    return " WORK";
                }
            }, WISH(6) {

                @Override
                public String toString() {
                    return " WISH";
                }
            }, JOIN(7) {

                @Override
                public String toString() {
                    return " JOIN";
                }
            }, CONS(8) {

                @Override
                public String toString() {
                    return " CONS";
                }
            }, STBL(9) {

                @Override
                public String toString() {
                    return " STBL";
                }
            }, SLEG(10) {

                @Override
                public String toString() {
                    return " SLEG";
                }
            }, HOLI(11) {

                @Override
                public String toString() {
                    return " HOLI";
                }
            };

    private final int value;

    private States(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getStringFromInt(int i) {
        System.out.println(i+"blahblahblahblahblah");
        for (States state : States.values()) {
            if (state.getValue() == i) {
                return state.toString();
            }
        }
        // throw an IllegalArgumentException or return null
        throw new IllegalArgumentException("estado não encontrado, não deve acontecer!!!!");
    }
}
