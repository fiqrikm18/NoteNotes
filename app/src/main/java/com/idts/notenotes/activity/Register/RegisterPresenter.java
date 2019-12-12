package com.idts.notenotes.activity.Register;

public class RegisterPresenter implements IRegisterPresenter {
    IRegisterView view;

    public RegisterPresenter(IRegisterView view) {
        this.view = view;
    }
}
