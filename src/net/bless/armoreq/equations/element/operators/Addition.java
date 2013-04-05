package net.bless.armoreq.equations.element.operators;

import net.bless.armoreq.equations.element.number.Number;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class Addition extends Operator{

        @Override
        protected Number eval(Number[] n) {
                return new Number(n[0].getValue() + n[1].getValue());
        }

        @Override
        public int operands() {
                return 2;
        }
        
        public String toString(){
                return "+";
        }

}