package net.bless.armoreq.equations.element.operators;

import net.bless.armoreq.equations.element.number.Number;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class Random extends Operator {

        @Override
        protected Number eval(Number[] n) {
                return new Number(Math.random());
        }

        @Override
        public int operands() {
                return 0;
        }

        @Override
        public String toString() {
                return "random";
        }

}