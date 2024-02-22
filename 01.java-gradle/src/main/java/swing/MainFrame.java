package swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private HomePanel homePanel;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        //JPanel homePanel = new JPanel();
//        homePanel.add(new JLabel("홈 화면"));
        homePanel = new HomePanel();
        tabbedPane.addTab("홈", homePanel);

        JPanel otherPanel = new JPanel();
        otherPanel.add(new JLabel("다른 화면"));
        tabbedPane.addTab("다른 화면", otherPanel);

        // 메뉴 생성 및 이벤트 처리
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("메뉴");

        JMenuItem homeItem = new JMenuItem("홈");
        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0); // 탭 인덱스 0은 홈 화면
            }
        });

        JMenuItem otherItem = new JMenuItem("다른 화면");
        otherItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1); // 탭 인덱스 1은 다른 화면
            }
        });

        menu.add(homeItem);
        menu.add(otherItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
