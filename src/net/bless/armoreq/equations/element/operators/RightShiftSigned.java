package net.bless.armoreq.equations.element.operators;

import  net.bless.armoreq.equations.element.number.Number;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class RightShiftSigned extends Operator {

        @Override
        protected Number eval(Number[] n) {
                return new Number(((int)n[0].getValue()) >> ((int)n[1].getValue()));
        }

        @Override
        public int operands() {
                return 2;
        }

        @Override
        public String toString() {
                return ">>";
        }

}