package com.nuc.t.Data;

import com.nuc.t.service.Manage;
import com.nuc.t.service.ShowMessage;

/**
 * Created by mxwbq on 2017/6/10.
 */
public class Data {
    ShowMessage showMessage = new ShowMessage();
    Manage manage = new Manage();
    Object[][] schoolData;
    Object[][] houseData;

    public ShowMessage getShowMessage() {
        return showMessage;
    }

    public Object[][] getSchoolData() {
        return showMessage.showSchoolUnit();
    }

    public Object[][] getHouseData(String name) {
        return showMessage.showHouse(manage.foundSchoolUnit(name));
    }
}
