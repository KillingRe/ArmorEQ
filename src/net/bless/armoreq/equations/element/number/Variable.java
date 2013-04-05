package net.bless.armoreq.equations.element.number;

import net.bless.armoreq.equations.element.number.Number;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class Variable extends Number{

        /* friendly */ Variable(double value, String name) {
                super(value);
        }
        
        public void setValue(double value){
                this.value = value;
        }
        
}