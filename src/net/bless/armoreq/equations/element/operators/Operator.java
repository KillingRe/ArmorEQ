package net.bless.armoreq.equations.element.operators;

import net.bless.armoreq.equations.element.Element;
import net.bless.armoreq.equations.element.ElementType;
import net.bless.armoreq.equations.element.number.Number;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public abstract class Operator extends Element {

        @Override
        public ElementType getType() {
                return ElementType.OPERATOR;
        }
        
        public Number evaluate(Number... n){
                if(n.length != operands())
                        throw new IllegalArgumentException("Must have " + operands() + " operands.");
                return eval(n);
        }
        
        protected abstract Number eval(Number[] n);
        
        public abstract int operands();

}