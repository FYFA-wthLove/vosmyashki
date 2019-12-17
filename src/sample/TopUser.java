package sample;

import java.util.Objects;

public class TopUser {
    private String nickName;

    public TopUser(String nickName) {
        this.nickName = nickName;
    }


    public TopUser (){}

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
