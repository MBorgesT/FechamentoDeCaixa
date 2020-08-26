package fechamentodecaixa;

import gui.MenuPrincipal;

public class FechamentoDeCaixa {

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        
        new MenuPrincipal().setVisible(true);
    }
    
}
