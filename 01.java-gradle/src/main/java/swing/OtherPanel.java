package swing;

import javax.swing.*;
import java.awt.*;

public class OtherPanel extends JPanel {
    public OtherPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("다른 화면"), BorderLayout.CENTER);
    }
}
