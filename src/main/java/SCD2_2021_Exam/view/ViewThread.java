package SCD2_2021_Exam.view;

public class ViewThread extends Thread {
    private MainFrame view;

    public ViewThread() {
    }

    public MainFrame getView() {
        return this.view;
    }

    public void run() {
        view = new MainFrame();
        view.setVisible(true);
    }
}