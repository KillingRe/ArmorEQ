package net.bless.armoreq.equations.element.operators;

import  net.bless.armoreq.equations.element.number.Number;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class Round extends Operator{

        @Override
        protected Number eval(Number[] n) {
                return new Number(Math.round(n[0].getValue()));
        }

        @Override
        public int operands() {
                return 1;
        }

        @Override
        public String toString() {
                return "round";
        }

}