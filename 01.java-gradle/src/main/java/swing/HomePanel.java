package swing;



import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;


public class HomePanel extends JPanel {
    private JButton autoClickBtn;
    private Timer timer;
    private Robot robot;
    private JCheckBox controlCheckbox;
    private boolean isMouseControlEnabled;
//    private JTextField delayTextField;
//    private JFormattedTextField delayTextField;
    private SpinnerNumberModel spinnerNumberModel;
    private Integer delayTime = 5000;


    public HomePanel() {
        try {
            robot = new Robot();
            setLayout(new BorderLayout());
        } catch (AWTException e) {
//            log.error(e.getMessage());
        }

        autoClickBtn = new JButton("오토 마우스 이동 버튼");
        controlCheckbox = new JCheckBox("ON/OFF");
//        delayTextField = new JTextField(5);

        spinnerNumberModel = new SpinnerNumberModel(5,3,50,1);
        JSpinner spinner = new JSpinner(spinnerNumberModel);
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor){
            JTextField textField = ((JSpinner.DefaultEditor)editor).getTextField();
            textField.setEditable(false);
//            textField.setBackground(Color.WHITE);
        }
//        delayTime = 5000;
        controlCheckbox.setEnabled(false);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("딜레이 (초)"));
        buttonPanel.add(spinner);
        buttonPanel.add(autoClickBtn);
        buttonPanel.add(controlCheckbox);

        add(buttonPanel, BorderLayout.NORTH);

        autoClickBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if ((int)spinner.getValue() < 3){
                        throw new NumberFormatException();
                    }else{
                        toggleAutoClick();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null,"3 이상의 수 입력하세요.");
                }
            }
        });

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delayTime = (int) spinner.getValue() * 1000;
            }
        });
        controlCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setMouseControlEnabled(controlCheckbox.isSelected());
            }
        });

        int delay = delayTime;
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMouseControlEnabled) {
                    performAutoClick();
                }
            }
        });
    }
    private void toggleAutoClick() {
        if (timer.isRunning()) {
            timer.stop();
            controlCheckbox.setSelected(false);
        } else {
            timer.setDelay(delayTime);
            timer.start();
            controlCheckbox.setSelected(true);
        }
    }


    private void performAutoClick() {

            Random random = new Random();
            int x = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width);
            int y = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height);
            robot.mouseMove(x,y);
        // 마우스 클릭
        //robot.mousePress(MouseEvent.BUTTON1_MASK);
        //robot.mouseRelease(MouseEvent.BUTTON1_MASK);

    }
    public void setMouseControlEnabled(boolean enabled){
        isMouseControlEnabled = enabled;
    }
}
