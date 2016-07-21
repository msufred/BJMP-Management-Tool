package com.gemseeker.edr.screen;

import com.gemseeker.edr.fxml.MainScreenController;

public interface ScreenController {
    public void initComponents();
    public void update();
    public void setMainFrame(MainScreenController mainFrame);
}
