/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfo;

/**
 *
 * @author diogo
 */
public enum States {

    REST {

                @Override
                public String toString() {
                    return " REST";
                }

            }, DECD {

                @Override
                public String toString() {
                    return " DECD";
                }

            }, MEET {

                @Override
                public String toString() {
                    return " MEET";
                }

            }, DIST {

                @Override
                public String toString() {
                    return " DIST";
                }
            }, WORK {

                @Override
                public String toString() {
                    return " WORK";
                }
            }, WISH {

                @Override
                public String toString() {
                    return " WISH";
                }
            }, JOIN {

                @Override
                public String toString() {
                    return " JOIN";
                }
            }, CONS {

                @Override
                public String toString() {
                    return " CONS";
                }
            }, STBL {

                @Override
                public String toString() {
                    return " STBL";
                }
            }, SLEG {

                @Override
                public String toString() {
                    return " SLEG";
                }
            }, HOLI {

                @Override
                public String toString() {
                    return " HOLI";
                }
            };
}
