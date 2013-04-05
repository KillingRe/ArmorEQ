package net.bless.armoreq.equations.element.number;

import net.bless.armoreq.equations.element.Element;
import net.bless.armoreq.equations.element.ElementType;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class Number extends Element {
        
        
        protected double value;

        public Number(double value){
                this.value = value;
        }

        @Override
        public ElementType getType() {
                return ElementType.NUMBER;
        }
        
        public double getValue(){
                return value;
        }
        
        @Override
        public String toString(){
                return String.valueOf(value);
        }

}
