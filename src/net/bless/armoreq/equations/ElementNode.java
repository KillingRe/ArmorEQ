package net.bless.armoreq.equations;

import net.bless.armoreq.equations.ElementNode;
import net.bless.armoreq.equations.element.Element;
/*
 * This code was taken from ChangeDamage
 * Author falsevacuum
 * License: GNU General Public License
 * 
 */
public class ElementNode {
        public ElementNode next;
        public ElementNode prev;
        private Element value;
        public Element getElement(){
                return value;
        }
        
        public ElementNode(Element value){
                this.value = value;
        }
}