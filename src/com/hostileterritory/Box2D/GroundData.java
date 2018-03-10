package com.hostileterritory.Box2D;

import com.hostileterritory.enums.UserDataType;

public class GroundData extends UserData {

    public GroundData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.GROUND;
    }
}

