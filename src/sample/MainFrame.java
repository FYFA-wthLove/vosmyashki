package sample;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends FrameCreator {
    private JTextField puzzleSideSize;
    public int width, height;

    public MainFrame(){
        super("Восьмяшки");
        setSize(250, 250);
        setLayout(new FlowLayout());
        height = 2;
        width = 1;
        setInterrupted(true);
        setVisible(true);
    }
}