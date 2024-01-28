package utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyClipBoard {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();

        StringSelection stringSelection = new StringSelection("이 문구가 복사 됩니다.");
        clipboard.setContents(stringSelection,null);
    }

}
