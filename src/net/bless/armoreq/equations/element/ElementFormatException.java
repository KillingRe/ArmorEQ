package net.bless.armoreq.equations.element;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class ElementFormatException extends RuntimeException {
        
        private static final long serialVersionUID = -6289476362327993062L;

        public ElementFormatException(String s){
                super("Illegal element: " + s);
        }
}