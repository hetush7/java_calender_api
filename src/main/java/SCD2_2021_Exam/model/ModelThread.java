package SCD2_2021_Exam.model;

import SCD2_2021_Exam.Presenter;
import SCD2_2021_Exam.facade.ApiFacade;
import SCD2_2021_Exam.view.MainFrame;

public class ModelThread extends Thread {
    private MainFrame view;
    private ApiFacade model;

    public ModelThread(ApiFacade model, MainFrame view) {
        this.view = view;
        this.model = model;
    }

    public void run() {
        Presenter presenter = new Presenter(view, model);
    }
}